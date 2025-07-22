package org.example.dao;
import org.example.model.Book;
import java.util.List;

public interface BookDao {
    void insert(Book book);
    List<Book> findAll();
    List<Book> findByReaderId(int readerId);
}
