package org.example.dao;

import org.example.model.BorrowedBook;

import java.util.List;

public interface BorrowedBookDao {
    void insert(BorrowedBook borrowedBook);
    List<BorrowedBook> findAll();
    List<BorrowedBook> findByReaderId(int readerId);
}
