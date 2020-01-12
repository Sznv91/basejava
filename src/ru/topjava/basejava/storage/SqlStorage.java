package ru.topjava.basejava.storage;

import org.postgresql.util.PSQLException;
import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.sql.BlockOfCode;
import ru.topjava.basejava.sql.ConnectionFactory;

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

        transExec("UPDATE resume SET full_name = ? WHERE uuid = ? ", new BlockOfCode<Object>() {
            @Override
            public Object exec(PreparedStatement ps) throws SQLException {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (1 > ps.executeUpdate()) {
                    throw new NotExistStorageException(resume.getUuid());
                }
                return null;
            }
        });
    }

    @Override
    public void clear() {
        transExec("DELETE FROM resume", new BlockOfCode<Object>() {
            @Override
            public Object exec(PreparedStatement ps) throws SQLException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void save(Resume resume) {

        transExec("INSERT INTO resume (uuid, full_name) VALUES (?,?)", new BlockOfCode<Object>() {
            @Override
            public Object exec(PreparedStatement ps) throws SQLException {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                try {
                    ps.execute();
                } catch (PSQLException q) {
                    throw new ExistStorageException(resume.getUuid());
                }
                return null;
            }
        });
    }

    @Override
    public Resume get(String uuid) {
        return transExec("SELECT * FROM resume WHERE uuid =?", new BlockOfCode<Resume>() {
            @Override
            public Resume exec(PreparedStatement ps) throws SQLException {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                return new Resume(uuid, rs.getString("full_name"));
            }
        });
    }

    @Override
    public void delete(String uuid) {
        transExec("DELETE FROM resume WHERE uuid=?", new BlockOfCode<Object>() {
            @Override
            public Object exec(PreparedStatement ps) throws SQLException {
                ps.setString(1, uuid);
                if (1 > ps.executeUpdate()) {
                    throw new NotExistStorageException(uuid);
                }
                return null;
            }
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<>();

        transExec("SELECT REPLACE(uuid,' ','') as uuid, full_name  FROM resume ORDER BY full_name,uuid",
                new BlockOfCode<Resume>() {
                    @Override
                    public Resume exec(PreparedStatement ps) throws SQLException {
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            result.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
                        }
                        return null;
                    }
                });

        return result;
    }

    @Override
    public int size() {
        int result = transExec("SELECT count(*) FROM resume", new BlockOfCode<Integer>() {
            @Override
            public Integer exec(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("count");
                } else {
                    return 0;
                }
            }
        });
        return result;
    }

    private <T> T transExec(String sqlReq, BlockOfCode<T> boc) {
        try (Connection connection = factory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlReq)) {
            return boc.exec(ps);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
