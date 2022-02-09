package com.webapp.sql;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import com.webapp.storage.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        /*
        this.connectionFactory = new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        };
        */
        this.connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM resume")) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException("Error delete data in table resume!", e);
        }
    }

    @Override
    public void save(Resume resume) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        } catch (SQLException e) {
            //throw new StorageException("Error get resume!", e);
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE resume SET uuid = ?, full_name = ? WHERE uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.setString(3, resume.getUuid());
            ps.execute();
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
        } catch (SQLException e) {
            throw new StorageException("Error get resume!", e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume WHERE uuid = ?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid, rs.getString("full_name"));
            return resume;
        } catch (SQLException e) {
            throw new StorageException("Error get resume!", e);
        }
    }

    @Override
    public void delete(String uuid) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM resume WHERE uuid = ?")) {
            ps.setString(1, uuid);
            if (!ps.execute()) {
                throw new NotExistStorageException("Resume - " + uuid + " not exists!");
            }
        } catch (SQLException e) {
            throw new StorageException("Error delete one resume in table resume!", e);
        }
    }

    @Override
    public Resume[] getAll() {
        return getAllSorted().toArray(new Resume[0]);
    }

    @Override
    public List<Resume> getAllSorted() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        } catch (SQLException e) {
            throw new StorageException("Error get resume!", e);
        }
    }

    @Override
    public int size() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM resume")) {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException("");
            }
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new StorageException("Error count resumes!", e);
        }
    }
}
