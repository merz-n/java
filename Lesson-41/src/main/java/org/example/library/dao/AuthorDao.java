package org.example.library.dao;

import org.example.library.model.Author;

import java.util.List;

public interface AuthorDao {
    void save(Author author);
    Author findById(Long id);
    List<Author> findAll();
    void update(Author author);
    void deleteById(Long id);
}
