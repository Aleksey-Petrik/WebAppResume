package com.webapp.sql;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {
            if ("23505".equals(e.getSQLState())) {
                return new ExistStorageException("Resume exists in DB!");
            }
        }
        return new StorageException(e);
    }
}
