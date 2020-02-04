package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.ContactType;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper helper;

    public SqlStorage(String urlDB, String userDB, String passwordDB) {
        helper = new SqlHelper(() -> DriverManager.getConnection(urlDB, userDB, passwordDB));
    }

    @Override
    public void update(Resume resume) {

        helper.<Void>executeTransaction(connection -> {
            try (PreparedStatement ps = connection.prepareStatement(
                    "UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }

            try (PreparedStatement ps = connection.prepareStatement("UPDATE contact " +
                    "SET type = ?, value = ? " +
                    "WHERE resume_uuid = ?")) {
                for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                    String uuid = resume.getUuid();
                    String contactType = entry.getKey().name();
                    String contactValue = entry.getValue();
                    ps.setString(1, uuid);
                    ps.setString(2, contactType);
                    ps.setString(3, contactValue);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            return null;
        });
    }

    @Override
    public void clear() {
        helper.execute("DELETE FROM resume", PreparedStatement::executeUpdate);

    }

    @Override
    public void save(Resume resume) {
        helper.<Void>executeTransaction(connection -> {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }


            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO contact (resume_uuid, value, type) " +
                            "VALUES (?,?,?)")) {
                for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, entry.getValue());
                    ps.setString(3, entry.getKey().name());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return helper.execute("SELECT uuid,full_name,type,value " +
                "FROM resume r " +
                "LEFT JOIN contact c " +
                "ON r.uuid = c.resume_uuid " +
                "WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume result = new Resume(uuid, rs.getString("full_name"));
            do {
                String contactType = rs.getString("type");
                String contactValue = rs.getString("value");
                if (contactType == null) {
                    break;
                }
                result.setContact(ContactType.valueOf(contactType), contactValue);
            } while (rs.next());
            return result;
        });
    }

    @Override
    public void delete(String uuid) {
        helper.<Void>execute("DELETE FROM resume WHERE uuid=?", ps -> {
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

        helper.execute("SELECT uuid, full_name,value,type " +
                        "FROM resume r " +
                        "LEFT JOIN contact c " +
                        "ON r.uuid = c.resume_uuid " +
                        "ORDER BY full_name,uuid",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Resume currentResume =
                                new Resume(rs.getString("uuid"), rs.getString("full_name"));

                        do {
                            String contactType = rs.getString("type");
                            String contactValue = rs.getString("value");
                            if (contactType == null) {
                                break;
                            }
                            currentResume.setContact(ContactType.valueOf(contactType), contactValue);
                        } while (rs.next());
                        result.add(currentResume);
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
