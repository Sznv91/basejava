package ru.topjava.basejava.sql;

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
            throw ExistExceptionUtil.convertException(e);
        }
    }

    public <T> void executeTransaction(BlocTransactionOfSqlCode<T> helper) {
        try (Connection connection = factory.getConnection()) {
            connection.setAutoCommit(false);
            T res = helper.exec(connection);
            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new StorageException("Exception in transaction. Make rollback", e.getSQLState(), e);
            }
        } catch (SQLException ex) {
            throw ExistExceptionUtil.convertException(ex);
        }
    }
}
