package org.example.dao;
import org.example.database.Database;
import org.example.model.BorrowedBook;
import org.example.model.Book;
import org.example.model.Reader;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowedBookDaoImpl implements BorrowedBookDao {
    private static final String INSERT_BORROWED_BOOK_SQL =
            "INSERT INTO borrowed_books (book_id, reader_id, borrow_date, return_date, status) " +
                    "VALUES (?, ?, ?, ?, ?) RETURNING id";

    private static final String SELECT_ALL_SQL =
            "SELECT bb.id, bb.borrow_date, bb.return_date, bb.status, " +
                    "b.id AS book_id, b.title, b.author, b.published_year, b.genre, " +
                    "r.id AS reader_id, r.name, r.email, r.phone " +
                    "FROM borrowed_books bb " +
                    "JOIN books b ON bb.book_id = b.id " +
                    "JOIN readers r ON bb.reader_id = r.id";

    private static final String SELECT_BY_READER_ID_SQL = SELECT_ALL_SQL + " WHERE r.id = ?";

    @Override
    public void insert(BorrowedBook bb) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_BORROWED_BOOK_SQL)) {

            stmt.setInt(1, bb.getBook().getId());
            stmt.setInt(2, bb.getReader().getId());
            stmt.setDate(3, Date.valueOf(bb.getBorrowDate()));
            stmt.setDate(4, bb.getReturnDate() == null ? null : Date.valueOf(bb.getReturnDate()));
            stmt.setString(5, bb.getStatus());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.out.println("✅ Запись о взятой книге добавлена, ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BorrowedBook> findAll() {
        List<BorrowedBook> list = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapBorrowedBook(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<BorrowedBook> findByReaderId(int readerId) {
        List<BorrowedBook> list = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_READER_ID_SQL)) {

            stmt.setInt(1, readerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapBorrowedBook(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private BorrowedBook mapBorrowedBook(ResultSet rs) throws SQLException {
        Book book = new Book(
                rs.getInt("book_id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("published_year"),
                rs.getString("genre")
        );
        Reader reader = new Reader(
                rs.getInt("reader_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone")
        );
        return new BorrowedBook(
                rs.getInt("id"),
                book,
                reader,
                rs.getDate("borrow_date").toLocalDate(),
                rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                rs.getString("status")
        );
    }

}
