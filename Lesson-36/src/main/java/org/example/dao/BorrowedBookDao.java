package org.example.dao;
import org.example.model.BorrowedBook;
import java.util.List;

public interface BorrowedBookDao {
    void insert(BorrowedBook borrowedBook);
    boolean addBorrowedBook(BorrowedBook borrowedBook);
    List<BorrowedBook> findAll();
    List<BorrowedBook> findByReaderId(int readerId);
    List<BorrowedBook> getBorrowedBooksByReaderId(int readerId);
}
