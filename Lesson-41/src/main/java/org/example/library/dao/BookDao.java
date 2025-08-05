package org.example.library.dao;

import org.example.library.model.Author;
import org.example.library.model.Book;

import java.util.List;

public interface BookDao {
    void save(Book book);
    Book findById(Long id);
    List<Book> findAll();
    void update(Book book);
    void deleteById(Long id);
}
