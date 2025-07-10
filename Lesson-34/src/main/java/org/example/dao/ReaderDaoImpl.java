package org.example.dao;

import org.example.database.Database;
import org.example.model.Book;
import org.example.model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderDaoImpl implements ReaderDao {
    private static final String INSERT_READER_SQL =
            "INSERT INTO readers (name, email, phone) " +
                    "VALUES (?, ?, ?) RETURNING id, name, email, phone";
    private static final String SELECT_ALL_READERS_SQL =
            "SELECT id, name, email, phone FROM readers";
    private static final String SELECT_READER_BY_ID_SQL =
            "SELECT id, name, email, phone FROM readers WHERE id = ?";

    @Override
    public void insert(Reader readerToSave) {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_READER_SQL)) {

            statement.setString(1, readerToSave.getName());
            statement.setString(2, readerToSave.getEmail());
            statement.setString(3, readerToSave.getPhone());


            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Reader inserted = mapReader(rs);
                System.out.println("✅ Вставлен читатель: " + inserted);
            } else {
                System.out.println("❌ Читатель не добавлен");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Reader> findAll() {
        List<Reader> readers = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_READERS_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                readers.add(mapReader(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readers;
    }

    @Override
    public Reader findById(int id) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_READER_BY_ID_SQL)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapReader(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Reader mapReader(ResultSet rs) throws SQLException {
        return new Reader(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone")
        );
    }
}
