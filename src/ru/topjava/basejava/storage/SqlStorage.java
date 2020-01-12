package ru.topjava.basejava.storage;

import org.postgresql.util.PSQLException;
import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.exeption.StorageException;
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

        try (Connection connection = factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE resume " +
                    "SET full_name = ? " +
                    "WHERE uuid = ? ");
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            if (1 > ps.executeUpdate()) {
                throw new NotExistStorageException(resume.getUuid());
            }
        } catch (SQLException e) {
            throw new StorageException("Can't update resume in DB", resume.getUuid(), e);
        }
    }

    @Override
    public void clear() {
        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException("can't make clear DB", "no uuid", e);
        }

    }

    @Override
    public void save(Resume resume) {
        try (Connection connection = factory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement
                    ("INSERT " +
                            "INTO resume (uuid, full_name) " +
                            "VALUES (?,?)");
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        } catch (PSQLException q) {
            throw new ExistStorageException(resume.getUuid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resume get(String uuid) {
        try (Connection connection = factory.getConnection();
             PreparedStatement ps = connection.prepareStatement
                     ("SELECT * FROM resume WHERE uuid =?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));

        } catch (SQLException e) {
            throw new StorageException(e.getMessage(), uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        try (Connection connection = factory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE " +
                     "FROM resume " +
                     "WHERE uuid=?")) {
            ps.setString(1, uuid);
            if (1 > ps.executeUpdate()) {
                throw new NotExistStorageException(uuid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Resume> getAllSorted() {

        List<Resume> result = new ArrayList<>();

        try (Connection connection = factory.getConnection();
             PreparedStatement ps = connection.prepareStatement
                     ("SELECT REPLACE(uuid,' ','') as uuid, full_name  FROM resume ORDER BY full_name,uuid")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Resume
                        (rs.getString("uuid")
                                , rs.getString("full_name")));
            }
            return result;

        } catch (SQLException e) {
            throw new StorageException(e.getMessage(), "no resumes in database");
        }
    }

    @Override
    public int size() {
//        final int[] result = new int[1];

        int result = transExec("SELECT count(*) FROM resume", new BlockOfCode<Integer>() {
            @Override
            public Integer exec(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return rs.getInt("count");
                } else {
                    return 0;
                }
            }
        });
        /*try (Connection connection = factory.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT count(*) FROM resume")) {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException("DB hasn't any record");
            }
            return rs.getInt("count");

        } catch (SQLException e) {
            throw new StorageException(e.getMessage(), "DB hasn't any record");
        }*/

        return result;
    }

    private <T> T transExec(String sqlReq, BlockOfCode<T> boc) {
        try (Connection connection = factory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlReq)) {
             return boc.exec(ps);
        } catch (SQLException e) {
            throw new IllegalStateException(e);// e.printStackTrace();
        }
        //boc.exec();
        //return null;
    }
}
