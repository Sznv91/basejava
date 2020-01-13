package ru.topjava.basejava.storage;

import org.postgresql.util.PSQLException;
import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.exeption.StorageException;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.sql.ConnectionFactory;
import ru.topjava.basejava.sql.BlocOfSqlCode;
import ru.topjava.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private final SqlHelper helper;

    public SqlStorage(String urlDB, String userDB, String passwordDB) {
        helper = new SqlHelper(new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(urlDB, userDB, passwordDB);
            }
        });
    }

    @Override
    public void update(Resume resume) {
        helper.execute("UPDATE resume SET full_name = ? WHERE uuid = ? ", ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public void clear() {
        helper.execute("DELETE FROM resume", (BlocOfSqlCode<Object>) PreparedStatement::executeUpdate);
    }

    @Override
    public void save(Resume resume) {
        helper.execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            try {
                ps.execute();
            } catch (PSQLException q) {
                if (q.getSQLState().equals("23505")) {
                    throw new ExistStorageException(resume.getUuid());
                } else {
                    throw new StorageException("SQL error", resume.getUuid(), q);
                }
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return helper.execute("SELECT * FROM resume WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        helper.execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<>();

        helper.execute("SELECT uuid, full_name  FROM resume ORDER BY full_name,uuid",
                (BlocOfSqlCode<Resume>) ps -> {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        result.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
                    }
                    return null;
                });

        return result;
    }

    @Override
    public int size() {
        return helper.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            } else {
                return 0;
            }
        });
    }


}
