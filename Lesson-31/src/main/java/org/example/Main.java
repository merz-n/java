package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        LibraryAPI libraryAPI = new LibraryAPIJDBC();

        //addBook

        Book newBook = new Book("Main 55 Test", "Main Author", 2024, "Fantasy");

        Book insertBook = libraryAPI.addBook(newBook);
        if(insertBook.getId() > 0){
            System.out.println("Книга успешно добавлена в базу данных! ID: " + insertBook.getId());
        }else{
            System.out.println("Книга НЕ добавлена в базу данных! ");
        }

        //addReader

        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "reader" + timestamp + "@example.com";
        String phone = "123" + timestamp.substring(timestamp.length() - 6);

        Reader newReader = new Reader("JUnit8r", email, phone);

        Reader insertReader = libraryAPI.addReader(newReader);
        if(insertReader.getId() > 0){
            System.out.println("Читатель успешно добавлен в базу данных! ID: " + insertReader.getId());
        } else {
            System.out.println("Читатель НЕ добавлен в базу данных!");
        }
        //borrowBook(выдача книги)
        OccupiedBook occupiedBook = libraryAPI.borrowBook(insertBook.getId(), insertReader.getId(), LocalDate.now());
        if (occupiedBook != null) {
            System.out.println("Книга выдана читателю!");
        } else {
            System.out.println("Ошибка при выдаче книги.");
        }


        //updateBookStatus
        int existingBookId = 1;

        Optional<Book> updatedBookOpt = libraryAPI.updateBookStatus(existingBookId, "returned");

        if (updatedBookOpt.isPresent()) {
            Book updatedBook = updatedBookOpt.get();
            System.out.println("Статус обновлён у книги: " + updatedBook.getTitle());
        } else {
            System.out.println("Обновление не выполнено, книга не найдена в borrowed_books.");
        }
        //updateBookStatus
        System.out.println("\nСписок всех занятых книг:");

        for (OccupiedBook ob : libraryAPI.getOccupiedBooks()) {
            System.out.println(
                    "ID: " + ob.getId() +
                            ", Книга: " + ob.getBook().getTitle() +
                            ", Читатель: " + ob.getReader().getName() +
                            ", Дата выдачи: " + ob.getBorrowDate() +
                            ", Статус: " + ob.getStatus()
            );
        }

        System.out.println("Обновляем данные читателя...");

        insertReader.setName("Updated Name");
        insertReader.setEmail("updated" + System.currentTimeMillis() + "@example.com");
        insertReader.setPhone("999" + (System.currentTimeMillis() % 1000000));

        Reader updatedReader = libraryAPI.updateReaderInfo(insertReader);

        if (updatedReader != null) {
            System.out.println("Данные читателя успешно обновлены:");
            System.out.println("Имя: " + updatedReader.getName());
            System.out.println("Email: " + updatedReader.getEmail());
            System.out.println("Телефон: " + updatedReader.getPhone());
        } else {
            System.out.println("Ошибка при обновлении данных читателя.");
        }

        System.out.println("Список книг со статусом 'borrowed':");

        List<Book> borrowedBooks = libraryAPI.filterBooksByStatus("borrowed");
        for (Book book : borrowedBooks) {
            System.out.println("ID: " + book.getId() + ", Название: " + book.getTitle());
        }

        System.out.println("Список книг со статусом 'returned':");

        List<Book> returnedBooks = libraryAPI.filterBooksByStatus("returned");
        for (Book book : returnedBooks) {
            System.out.println("ID: " + book.getId() + ", Название: " + book.getTitle());
        }
        // findBooksAfterDateNotReturned
        System.out.println("Список книг, выданных после указанной даты и не возвращённых:");

        LocalDate searchDate = LocalDate.now().minusDays(1);
        List<Book> notReturnedBooks = libraryAPI.findBooksAfterDateNotReturned(searchDate);

        for (Book book : notReturnedBooks) {
            System.out.println("ID: " + book.getId() + ", Название: " + book.getTitle());
        }
    }
}