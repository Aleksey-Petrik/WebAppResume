package com.webapp.storage;

import com.webapp.exception.NotExistStorageException;
import com.webapp.model.AbstractSection;
import com.webapp.model.ContactType;
import com.webapp.model.Resume;
import com.webapp.model.SectionType;
import com.webapp.sql.SqlHelper;
import com.webapp.util.JsonParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        //Инициализация драйвера Базы Данных
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("No driver found for PostgreSQL database", e);
        }
        /*
        this.connectionFactory = new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        };
        */
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.sqlExecutor("DELETE FROM resume");
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.sqlTransactionExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            deleteContacts(connection, resume.getUuid());
            deleteSections(connection, resume.getUuid());
            insertContacts(resume, connection);
            insertSections(resume, connection);
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.sqlTransactionExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                ps.execute();
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteContacts(connection, resume.getUuid());
            deleteSections(connection, resume.getUuid());
            insertContacts(resume, connection);
            insertSections(resume, connection);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.sqlTransactionExecute(connection -> {
            Resume resume;
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume WHERE uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM contact WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, resume);
                }
            }

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.sqlExecute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (!ps.execute()) {
                throw new NotExistStorageException("Resume - " + uuid + " not exists!");
            }
            return null;
        });
    }

    @Override
    public Resume[] getAll() {
        return getAllSorted().toArray(new Resume[0]);
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.sqlTransactionExecute(connection -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = new Resume(rs.getString("uuid"), rs.getString("full_name"));
                    resumes.put(resume.getUuid(), resume);
                }
            }

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, resumes.get(rs.getString("resume_uuid")));
                }
            }

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, resumes.get(rs.getString("resume_uuid")));
                }
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.sqlExecute("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void deleteAttributes(Connection connection, String uuid, String sql) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }

    private void deleteContacts(Connection connection, String uuid) throws SQLException {
        deleteAttributes(connection, uuid, "DELETE FROM contact WHERE resume_uuid = ?");
    }

    private void deleteSections(Connection connection, String uuid) throws SQLException {
        deleteAttributes(connection, uuid, "DELETE FROM section WHERE resume_uuid = ?");
    }

    private void insertContacts(Resume resume, Connection connection) throws SQLException {
        //try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (type, value, resume_uuid) VALUES (?, ?, ?)")) {
        //TODO test sql query...
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (type, value, resume_uuid) VALUES (?, ?, ?) ON CONFLICT (type, resume_uuid) DO UPDATE SET value = excluded.value")) {
            for (Map.Entry<ContactType, String> contact : resume.getContacts().entrySet()) {
                ps.setString(1, contact.getKey().name());
                ps.setString(2, contact.getValue());
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSections(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO section (type, value, resume_uuid) VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, AbstractSection> section : resume.getSections().entrySet()) {
                ps.setString(1, section.getKey().name());
                ps.setString(2, JsonParser.write(section.getValue(), AbstractSection.class));
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        SectionType sectionType = SectionType.valueOf(rs.getString("type"));
        resume.addSection(sectionType, JsonParser.read(rs.getString("value"), AbstractSection.class));
    }

}
