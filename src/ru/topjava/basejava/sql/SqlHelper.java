package ru.topjava.basejava.sql;

import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory factory;

    public SqlHelper(ConnectionFactory factory) {
        this.factory = factory;
    }

    public <T> T execute(String sqlReq, BlocOfSqlCode<T> helper) {
        try (Connection connection = factory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlReq)) {
            return helper.exec(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(e.getMessage());
            }
            throw new StorageException("SQL error", sqlReq, e);
        }
    }
}
