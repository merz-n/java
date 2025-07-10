package org.example.dao;

import org.example.model.Book;
import org.example.model.Reader;

import java.util.List;

public interface ReaderDao {
    void insert(Reader reader);
    List<Reader> findAll();
    Reader findById(int id);
}
