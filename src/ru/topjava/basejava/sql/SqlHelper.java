package ru.topjava.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlHelper<T> {
    T exec(PreparedStatement ps) throws SQLException;
}
