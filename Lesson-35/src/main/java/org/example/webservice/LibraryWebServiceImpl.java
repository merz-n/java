package org.example.webservice;

import jakarta.jws.WebService;
import org.example.dao.*;
import org.example.database.Database;
import org.example.model.Book;
import org.example.model.BorrowedBook;
import org.example.model.Reader;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@WebService(endpointInterface = "org.example.webservice.LibraryWebService")
public class LibraryWebServiceImpl implements LibraryWebService{


    private final BookDao bookDao = new BookDaoImpl();
    private final ReaderDao readerDao = new ReaderDaoImpl();
    private final BorrowedBookDao borrowedBookDao = new BorrowedBookDaoImpl();


    @Override
    public String addBook(String title, String author, int publishedYear, String genre) {
        Book book = new Book(title, author, publishedYear, genre);
        boolean result = bookDao.addBook(book);
        return result ? "Книга добавлена" : "Ошибка при добавлении книги";
    }

    @Override
    public String addReader(String name, String email, String phone) {
        Reader reader = new Reader(name, email, phone);
        boolean result = readerDao.addReader(reader);
        return result ? "Читатель добавлен" : "Ошибка при добавлении читателя";
    }
    @Override
    public String reserveBook(int bookId, int readerId, String status) {
        Book book = new Book();
        book.setId(bookId);

        Reader reader = new Reader();
        reader.setId(readerId);

        BorrowedBook borrowedBook = new BorrowedBook(book, reader, LocalDate.now(), null, status);

        boolean result = borrowedBookDao.addBorrowedBook(borrowedBook);
        return result ? "Резервация выполнена" : "Ошибка при резервировании";
    }
    @Override
    public String getBorrowedBooksByReaderId(int readerId) {
        List<BorrowedBook> books = borrowedBookDao.getBorrowedBooksByReaderId(readerId);

        if (books.isEmpty()) {
            return " У читателя нет выданных книг.";
        }

        StringBuilder sb = new StringBuilder("Список выданных книг:\n");

        for (BorrowedBook b : books) {
            sb.append(" Книга: ").append(b.getBook().getTitle())
                    .append(", Автор: ").append(b.getBook().getAuthor())
                    .append(", Дата выдачи: ").append(b.getBorrowDate())
                    .append(", Статус: ").append(b.getStatus());

            if (b.getReturnDate() != null) {
                sb.append(", Дата возврата: ").append(b.getReturnDate());
            }

            sb.append("\n");
        }

        return sb.toString();
    }

}
