package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryAPITest {
    private final  LibraryAPI libraryAPI = new LibraryAPIJDBC();

    @Test
    public void testAddBook(){
        Book newBook = new Book("Main", "Main Tathor", 2024, "Fantasy");

        Book insertBook = libraryAPI.addBook(newBook);
        assertNotNull(insertBook, "Результат не должен быть null");
        assertTrue(insertBook.getId() > 0, "ID должен быть больше 0");
        assertEquals("Main", insertBook.getTitle(), "Название книги должно совпадать");
    }

    @Test
    public void testAddReader(){
        String timestamp = String.valueOf(System.currentTimeMillis());
        String uniqueEmail = "reader" + timestamp + "@example.com";
        String uniquePhone = "123" + timestamp.substring(timestamp.length() - 6);

        Reader newReader = new Reader("JUnit Reader", uniqueEmail, uniquePhone);
        Reader insertedReader = libraryAPI.addReader(newReader);

        assertNotNull(insertedReader, "Результат не должен быть null");
        assertTrue(insertedReader.getId() > 0, "ID должен быть больше 0");
        assertEquals("JUnit Reader", insertedReader.getName(), "Name должно совпадать");
    }

    @Test
    public void testUpdateBookStatus() {
        int existingBookId = 19;

        Optional<Book> updatedBookOpt = libraryAPI.updateBookStatus(existingBookId, "returned");

        assertTrue(updatedBookOpt.isPresent(), "Книга должна быть найдена");
        Book updatedBook = updatedBookOpt.get();
        assertEquals(existingBookId, updatedBook.getId(), "ID книги должен совпадать");
    }

    @Test
    public void testBorrowBook() {
        Book newBook = new Book("Test Borrow Book", "Borrow Author", 2024, "Science");
        Book insertedBook = libraryAPI.addBook(newBook);

        String timestamp = String.valueOf(System.currentTimeMillis());
        String uniqueEmail = "occupied" + timestamp + "@example.com";
        String uniquePhone = "123" + timestamp.substring(timestamp.length() - 6);
        Reader newReader = new Reader("Occupied Reader", uniqueEmail, uniquePhone);
        Reader insertedReader = libraryAPI.addReader(newReader);

        LocalDate borrowDate = LocalDate.now();
        OccupiedBook occupiedBook = libraryAPI.borrowBook(insertedBook.getId(), insertedReader.getId(), borrowDate);

        assertNotNull(occupiedBook, "OccupiedBook не должен быть null");
        assertEquals(insertedBook.getId(), occupiedBook.getBook().getId(), "ID книги должен совпадать");
        assertEquals(insertedReader.getId(), occupiedBook.getReader().getId(), "ID читателя должен совпадать");
        assertEquals("borrowed", occupiedBook.getStatus(), "Статус должен быть 'borrowed'");
    }
    @Test
    public void testGetOccupiedBooks() {
        // 1. Добавляем новую книгу
        Book newBook = new Book("Occupied Test Book", "Occupied Author", 2024, "Drama");
        Book insertedBook = libraryAPI.addBook(newBook);

        // 2. Создаём уникального читателя
        String timestamp = String.valueOf(System.currentTimeMillis());
        String uniqueEmail = "occupied" + timestamp + "@example.com";
        String uniquePhone = "123" + timestamp.substring(timestamp.length() - 6);
        Reader newReader = new Reader("Occupied Reader", uniqueEmail, uniquePhone);
        Reader insertedReader = libraryAPI.addReader(newReader);

        LocalDate borrowDate = LocalDate.now();
        OccupiedBook occupiedBook = libraryAPI.borrowBook(insertedBook.getId(), insertedReader.getId(), borrowDate);


        List<OccupiedBook> occupiedBooks = libraryAPI.getOccupiedBooks();


        boolean found = false;
        for (OccupiedBook ob : occupiedBooks) {
            if (ob.getId() == occupiedBook.getId()
                    && ob.getBook().getId() == insertedBook.getId()
                    && ob.getReader().getId() == insertedReader.getId()) {
                found = true;
                break;
            }
        }

        assertTrue(found, "Запись о занятости книги должна быть найдена в списке.");
    }
    @Test
    public void testUpdateReaderInfo() {

        String timestamp = String.valueOf(System.currentTimeMillis());
        String uniqueEmail = "reader" + timestamp + "@example.com";
        String uniquePhone = "123" + timestamp.substring(timestamp.length() - 6);

        Reader newReader = new Reader("Original Reader", uniqueEmail, uniquePhone);
        Reader insertedReader = libraryAPI.addReader(newReader);


        String updatedEmail = "updated" + timestamp + "@example.com";
        String updatedPhone = "999" + timestamp.substring(timestamp.length() - 6);

        insertedReader.setName("Updated Reader");
        insertedReader.setEmail(updatedEmail);
        insertedReader.setPhone(updatedPhone);

        Reader updatedReader = libraryAPI.updateReaderInfo(insertedReader);

        assertNotNull(updatedReader, "Обновлённый читатель не должен быть null");
        assertEquals("Updated Reader", updatedReader.getName(), "Имя должно быть обновлено");
        assertEquals(insertedReader.getId(), updatedReader.getId(), "ID должен остаться тем же");
    }

    @Test
    public void testFilterBooksByStatus() {

        Book newBook = new Book("Filter  Book", "Filter Author", 2024, "History");
        Book insertedBook = libraryAPI.addBook(newBook);

        String timestamp = String.valueOf(System.currentTimeMillis());
        String uniqueEmail = "occupied" + timestamp + "@example.com";
        String uniquePhone = "123" + timestamp.substring(timestamp.length() - 6);
        Reader newReader = new Reader("Occupied Reader", uniqueEmail, uniquePhone);
        Reader insertedReader = libraryAPI.addReader(newReader);

        libraryAPI.borrowBook(insertedBook.getId(), insertedReader.getId(), LocalDate.now());

        List<Book> borrowedBooks = libraryAPI.filterBooksByStatus("borrowed");

        boolean found = false;
        for (Book book : borrowedBooks) {
            if (book.getId() == insertedBook.getId()) {
                found = true;
                break;
            }
        }

        assertTrue(found, "Книга должна быть найдена среди книг со статусом 'borrowed'");
    }
    @Test
    public void testFindBooksAfterDateNotReturned() {
        Book newBook = new Book("NotReturned Tes Book", "Authorе е", 2024, "Novel");
        Book insertedBook = libraryAPI.addBook(newBook);

        String timestamp = String.valueOf(System.currentTimeMillis());
        String uniqueEmail = "occupied" + timestamp + "@example.com";
        String uniquePhone = "123" + timestamp.substring(timestamp.length() - 6);
        Reader newReader = new Reader("Occupied Reader", uniqueEmail, uniquePhone);
        Reader insertedReader = libraryAPI.addReader(newReader);

        libraryAPI.borrowBook(insertedBook.getId(), insertedReader.getId(), LocalDate.now());

        List<Book> notReturnedBooks = libraryAPI.findBooksAfterDateNotReturned(LocalDate.now().minusDays(1));

        boolean found = false;
        for (Book book : notReturnedBooks) {
            if (book.getId() == insertedBook.getId()) {
                found = true;
                break;
            }
        }

        assertTrue(found, "Книга должна быть найдена среди невозвращённых после указанной даты.");
    }
}
