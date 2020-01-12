package ru.topjava.basejava.storage;

import org.postgresql.util.PSQLException;
import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.sql.ConnectionFactory;
import ru.topjava.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private final ConnectionFactory factory;

    public SqlStorage(String urlDB, String userDB, String passwordDB) {
        factory = () -> DriverManager.getConnection(urlDB, userDB, passwordDB);
    }

    @Override
    public void update(Resume resume) {
        execute("UPDATE resume SET full_name = ? WHERE uuid = ? ", ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            if (1 > ps.executeUpdate()) {
                throw new NotExistStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public void clear() {
        execute("DELETE FROM resume", (SqlHelper<Object>) PreparedStatement::executeUpdate);
    }

    @Override
    public void save(Resume resume) {
        execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            try {
                ps.execute();
            } catch (PSQLException q) {
                throw new ExistStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return execute("SELECT * FROM resume WHERE uuid =?", ps -> {
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
        execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (1 > ps.executeUpdate()) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<>();

        execute("SELECT REPLACE(uuid,' ','') as uuid, full_name  FROM resume ORDER BY full_name,uuid",
                (SqlHelper<Resume>) ps -> {
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
        int result = execute("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            } else {
                return 0;
            }
        });
        return result;
    }

    private <T> T execute(String sqlReq, SqlHelper<T> helper) {
        try (Connection connection = factory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlReq)) {
            return helper.exec(ps);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
