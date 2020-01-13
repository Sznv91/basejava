package ru.topjava.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface BlocOfSqlCode<T> {
    T exec(PreparedStatement ps) throws SQLException;
}
