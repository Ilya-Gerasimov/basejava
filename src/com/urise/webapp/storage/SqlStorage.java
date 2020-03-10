package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (type, value, resume_uuid) VALUES (?,?,?)")) {
                        for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                            exchangeContact(ps, e, resume);
                        }
                        ps.executeBatch();
                    }
                    return null;
                }
        );
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContact(rs, resume);
                    } while (rs.next());

                    return resume;
                });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE contact SET type = ?, value = ? WHERE resume_uuid = ?")) {
                for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                    exchangeContact(ps, e, resume);
                }
                ps.executeBatch();
            }
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("" +
                "    SELECT * FROM resume r" +
                " LEFT JOIN contact c ON r.uuid = c.resume_uuid" +
                "  ORDER BY full_name, uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> map = new LinkedHashMap<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                Resume resume = map.get(uuid);
                if (resume == null) {
                    resume = new Resume(uuid, rs.getString("full_name"));
                    map.put(uuid, resume);
                }
                addContact(rs, resume);
            }
            return new ArrayList<>(map.values());
        });
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void exchangeContact(PreparedStatement ps, Map.Entry<ContactType, String> e, Resume resume) throws SQLException {
        ps.setString(1, e.getKey().name());
        ps.setString(2, e.getValue());
        ps.setString(3, resume.getUuid());
        ps.addBatch();
    }
}
