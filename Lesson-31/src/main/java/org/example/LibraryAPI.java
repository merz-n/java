package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LibraryAPI {
    Book addBook(Book book);
    Optional<Book> updateBookStatus(int bookId, String status);
    Reader addReader(Reader reader);
    Reader updateReaderInfo(Reader reader);
    List<OccupiedBook> getOccupiedBooks();
    List<Book> filterBooksByStatus(String status);
    List<Book> findBooksAfterDateNotReturned(LocalDate date);
    OccupiedBook borrowBook(int bookId, int readerId, LocalDate borrowDate);
}
