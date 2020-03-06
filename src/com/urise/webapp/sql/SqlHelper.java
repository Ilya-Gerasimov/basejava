package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public <T> T execute(String sql, SqlProcessor<T> processor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return processor.process(ps);
        } catch (SQLException e) {
//            System.out.println("код ошибки " + e.getSQLState());
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(null);
            } else {
                throw new StorageException(e);
            }
        }
    }

    public interface SqlProcessor<T> {
        T process(PreparedStatement st) throws SQLException;
    }
}
