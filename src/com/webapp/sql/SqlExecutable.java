package com.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlExecutable<T> {
    T execute(PreparedStatement ps) throws SQLException;
}
