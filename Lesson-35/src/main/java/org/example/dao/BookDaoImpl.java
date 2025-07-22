package org.example.dao;

import org.example.database.Database;
import org.example.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao{
    private static final String INSERT_BOOK_SQL =
            "INSERT INTO books (title, author, published_year, genre) " +
                    "VALUES (?, ?, ?, ?) RETURNING id, title, author, published_year, genre";

    private static final String SELECT_ALL_BOOKS_SQL =
            "SELECT id, title, author, published_year, genre FROM books";

    private static final String SEARCH_BORROWED_BOOKS_BY_READERS =
            "SELECT b.id, b.title, b.author, b.published_year, b.genre " +
                    "FROM books b " +
                    "JOIN borrowed_books bb ON b.id = bb.book_id " +
                    "WHERE bb.reader_id = ? AND bb.status = 'borrowed'";

    @Override
    public void insert(Book bookToSave) {
        System.out.println("📥 Добавляю книгу в базу: " + bookToSave);
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_BOOK_SQL)) {

            statement.setString(1, bookToSave.getTitle());
            statement.setString(2, bookToSave.getAuthor());
            statement.setInt(3, bookToSave.getPublishedYear());
            statement.setString(4, bookToSave.getGenre());


            System.out.println("📥 Добавляю книгу: " + bookToSave);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Book inserted = mapBook(rs);
                System.out.println("✅ Вставлена книга: " + inserted);
            } else {
                System.out.println("❌ Книга не добавлена");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOKS_SQL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                books.add(mapBook(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findByReaderId(int readerId) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SEARCH_BORROWED_BOOKS_BY_READERS)) {
            statement.setInt(1, readerId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    books.add(mapBook(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public boolean addBook(Book bookToSave) {
        String sql = "INSERT INTO books (title, author, published_year, genre) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookToSave.getTitle());
            stmt.setString(2, bookToSave.getAuthor());
            stmt.setInt(3, bookToSave.getPublishedYear());
            stmt.setString(4, bookToSave.getGenre());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Book mapBook(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("published_year"),
                rs.getString("genre")
        );
    }
}
