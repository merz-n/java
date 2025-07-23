package org.example.dao;
import java.util.List;
import org.example.model.Book;

public interface BookDao {
    void insert(Book book);
    boolean addBook(Book bookToSave);
    List<Book> findAll();
    List<Book> findByReaderId(int readerId);
}
