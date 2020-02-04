package ru.topjava.basejava.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface BlocTransactionOfSqlCode<T> {
    T exec(Connection connection) throws SQLException;
}
