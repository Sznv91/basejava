package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.*;
import ru.topjava.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper helper;

    public SqlStorage(String urlDB, String userDB, String passwordDB) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM contact WHERE resume_uuid=?")) {
                ps.setString(1, resume.getUuid());
            }
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM sections WHERE resume_uuid=?")) {
                ps.setString(1, resume.getUuid());
            }
            addContacts(resume, connection);
            addSections(resume, connection);
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
            addContacts(resume, connection);
            addSections(resume, connection);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return helper.execute("SELECT uuid,full_name,type, value, section_type, content, section_name " +
                "FROM resume r " +
                "LEFT JOIN contact c " +
                "ON r.uuid = c.resume_uuid " +
                "LEFT JOIN sections s " +
                "ON r.uuid = s.resume_uuid " +
                "WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid, rs.getString("full_name"));
            do {
                readContacts(resume, rs);
                readSections(resume, rs);
            }
            while (rs.next());
            return resume;
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

    /*@Override
    public List<Resume> getAllSorted() {
        return helper.execute("SELECT uuid,full_name,type, value, section_type, content, section_name " +
                        "FROM resume r " +
                        "LEFT JOIN contact c " +
                        "ON r.uuid = c.resume_uuid " +
                        "LEFT JOIN sections s " +
                        "ON r.uuid = s.resume_uuid " +
                        "ORDER BY full_name,uuid",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    Map<String, Resume> resumeMap = new LinkedHashMap<>();
                    while (rs.next()) {
                        String currentUuid = rs.getString("uuid");
                        String fullName = rs.getString("full_name");
                        Resume currentResume = resumeMap.computeIfAbsent(currentUuid, k -> new Resume(currentUuid, fullName));
                        readContacts(currentResume, rs);
                        readSections(currentResume, rs);
                    }
                    return new ArrayList<>(resumeMap.values());
                });
    }*/

    @Override
    public List<Resume> getAllSorted() { //getAllSortedTwoReq()
        List<Resume> result = new ArrayList<>();
        helper.executeTransaction(connection -> {
            ResultSet uuidList = connection.prepareStatement("SELECT * FROM resume ORDER BY full_name,uuid").executeQuery();
            ResultSet contactsList = connection.prepareStatement("SELECT * FROM contact", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery();
            ResultSet sectionsList = connection.prepareStatement("SELECT * FROM sections", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery();


            while (uuidList.next()) {
                String uuid = uuidList.getString("uuid");
                String fullName = uuidList.getString("full_name");
                Resume currentResume = new Resume(uuid, fullName);
                while (contactsList.next()) {
                    if (contactsList.getString("resume_uuid").equals(uuid)) {
                        readContacts(currentResume, contactsList);
                    }
                }
                contactsList.beforeFirst();
                while (sectionsList.next()) {
                    if (sectionsList.getString("resume_uuid").equals(uuid)) {
                        readSections(currentResume, sectionsList);
                    }
                }
                sectionsList.beforeFirst();
                result.add(currentResume);
            }
            return null;
        });
        //result.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
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

    private void addContacts(Resume resume, Connection connection) throws SQLException {
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
    }

    private void readContacts(Resume resume, ResultSet rs) throws SQLException {
        String contactType = rs.getString("type");
        String contactValue = rs.getString("value");
        if (contactType != null) {
            resume.setContact(ContactType.valueOf(contactType), contactValue);
        }
    }

    private void addSections(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO sections (resume_uuid, section_type, content, section_name) VALUES (?,?,?,?)")) {

            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getValue().getClass().getName());
                ps.setString(4, entry.getKey().name());

                switch (entry.getKey().name()) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        writeTextSection(entry.getValue(), ps);
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        writeListSection(entry.getValue(), ps);
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        writeCompanySection();
                        break;
                }
            }
            ps.executeBatch();
        }
    }

    private void readSections(Resume resume, ResultSet rs) throws SQLException {
        if (rs.getString("section_type") != null) {
            switch (rs.getString("section_name")) {
                case "PERSONAL":
                case "OBJECTIVE":
                    readTextSection(resume, rs);
                    break;
                case "ACHIEVEMENT":
                case "QUALIFICATIONS":
                    readListSection(resume, rs);
                    break;
                case "EXPERIENCE":
                case "EDUCATION":
                    readCompanySection(resume, rs);
                    break;
            }
        }
    }

    private void readTextSection(Resume resume, ResultSet rs) throws SQLException {
        TextSection section = new TextSection(rs.getString("content"));
        resume.setSection(SectionType.valueOf(rs.getString("section_name")), section);
    }

    private void readListSection(Resume resume, ResultSet rs) throws SQLException {
        String notSplitString = rs.getString("content");
        String[] splitString = notSplitString.split("\r\n");
        List<String> result = new ArrayList<>(Arrays.asList(splitString));
        ListSection section = new ListSection(result);
        resume.setSection(SectionType.valueOf(rs.getString("section_name")), section);
    }

    private void writeTextSection(AbstractSection value, PreparedStatement ps) throws SQLException {
        ps.setString(3, ((TextSection) value).getContent());
        ps.addBatch();
    }

    private void writeListSection(AbstractSection value, PreparedStatement ps) throws SQLException {
        ListSection section = (ListSection) value;
        StringBuilder builder = new StringBuilder();
        for (String i : section.getContent()) {
            builder.append(i).append(System.lineSeparator());
        }
        ps.setString(3, builder.substring(0, builder.length() - 2));
        ps.addBatch();
    }

    private void writeCompanySection() {
    }
    private void readCompanySection(Resume resume, ResultSet rs) {
    }
}
