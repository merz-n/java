package org.example.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "borrowed_books", schema = "public")
public class BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;
    @Column(name = "borrow_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate borrowDate;
    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private LocalDate returnDate;
    @Column(nullable = false)
    private String status;

    public BorrowedBook(Book book, Reader reader, LocalDate borrowDate, LocalDate returnDate, String status) {
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public BorrowedBook(Integer id, Book book, Reader reader, LocalDate borrowDate, LocalDate returnDate, String status) {
        this.id = id;
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OccupiedBook{" +
                "id=" + id +
                ", book=" + book +
                ", reader=" + reader +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                '}';
    }
}
