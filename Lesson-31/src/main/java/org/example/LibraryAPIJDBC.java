package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Optional;


public class LibraryAPIJDBC implements LibraryAPI{

    @Override
    public Book addBook(Book book) {
        String sql = "INSERT INTO BOOKS(title, author, published_year, genre) VALUES (?,?,?,?)";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPublishedYear());
            statement.setString(4, book.getGenre());

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Ошибка при добавлении книги.");
            }
            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    int newId = generatedKeys.getInt(1);
                    book.setId(newId);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Optional<Book> updateBookStatus(int bookId, String status) {
        if (!status.equals("borrowed") && !status.equals("returned")) {
            throw new IllegalArgumentException("Недопустимый статус: " + status);
        }

        String sql = "UPDATE borrowed_books SET status = ? WHERE book_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, bookId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Нет книги с таким ID в borrowed_books, статус не обновлён.");
                return Optional.empty();
            } else {
                return Optional.ofNullable(getBookById(bookId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Reader addReader(Reader reader) {
        String sql = "INSERT INTO readers(name, email, phone) VALUES (?,?,?)";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,reader.getName());
            statement.setString(2, reader.getEmail());
            statement.setString(3, reader.getPhone());

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Ошибка при добавлении читателя.");
            }
            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    int newId = generatedKeys.getInt(1);
                    reader.setId(newId);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reader;
    }

    @Override
    public Reader updateReaderInfo(Reader reader) {
        String sql = "UPDATE readers SET name = ?, email = ?, phone = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, reader.getName());
            statement.setString(2, reader.getEmail());
            statement.setString(3, reader.getPhone());
            statement.setInt(4, reader.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Читатель с ID " + reader.getId() + " не найден.");
                return null;
            }

            return reader;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<OccupiedBook> getOccupiedBooks() {
        List<OccupiedBook> occupiedBooks = new ArrayList<>();

        String sql = """
            SELECT bb.id as borrowed_id,
                   b.id as book_id, b.title, b.author, b.published_year, b.genre,
                   r.id as reader_id, r.name, r.email, r.phone,
                   bb.borrow_date, bb.return_date, bb.status
            FROM borrowed_books bb
            JOIN books b ON bb.book_id = b.id
            JOIN readers r ON bb.reader_id = r.id
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
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

                LocalDate borrowDate = rs.getDate("borrow_date").toLocalDate();
                LocalDate returnDate = rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null;
                String status = rs.getString("status");

                OccupiedBook occupiedBook = new OccupiedBook(
                        rs.getInt("borrowed_id"),
                        book,
                        reader,
                        borrowDate,
                        returnDate,
                        status
                );

                occupiedBooks.add(occupiedBook);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return occupiedBooks;
    }

    @Override
    public List<Book> filterBooksByStatus(String status) {
        List<Book> books = new ArrayList<>();

        String sql = """
            SELECT b.id, b.title, b.author, b.published_year, b.genre
            FROM borrowed_books bb
            JOIN books b ON bb.book_id = b.id
            WHERE bb.status = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("published_year"),
                        rs.getString("genre")
                );
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public List<Book> findBooksAfterDateNotReturned(LocalDate date) {
        List<Book> books = new ArrayList<>();

        String sql = """
            SELECT b.id, b.title, b.author, b.published_year, b.genre
            FROM borrowed_books bb
            JOIN books b ON bb.book_id = b.id
            WHERE bb.borrow_date > ? AND bb.status = 'borrowed'
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, Date.valueOf(date));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("published_year"),
                        rs.getString("genre")
                );
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    private Book getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bookId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("published_year"),
                        rs.getString("genre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public OccupiedBook borrowBook(int bookId, int readerId, LocalDate borrowDate) {
        String sql = "INSERT INTO borrowed_books (book_id, reader_id, borrow_date, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, bookId);
            statement.setInt(2, readerId);
            statement.setDate(3, Date.valueOf(borrowDate));
            statement.setString(4, "borrowed");

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Ошибка при выдаче книги.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    Book book = getBookById(bookId);
                    Reader reader = getReaderById(readerId);
                    return new OccupiedBook(newId, book, reader, borrowDate, null, "borrowed");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Reader getReaderById(int readerId) {
        String sql = "SELECT * FROM readers WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, readerId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Reader(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
