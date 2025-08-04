package org.example;
import org.example.library.config.AppConfig;
import org.example.library.dao.AuthorDao;
import org.example.library.dao.BookDao;
import org.example.library.model.Author;
import org.example.library.model.Book;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AuthorDao authorDao = context.getBean(AuthorDao.class);
        BookDao bookDao = context.getBean(BookDao.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n Меню:");
            System.out.println("1 - Добавить автора");
            System.out.println("2 - Показать всех авторов");
            System.out.println("3 - Удалить автора");
            System.out.println("4 - Добавить книгу");
            System.out.println("5 - Показать все книги");
            System.out.println("6 - Удалить книгу");
            System.out.println("7 - Обновить книгу");
            System.out.println("8 - Обновить автора");
            System.out.println("0 - Выход");

            System.out.print("Введите команду: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите имя автора: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите страну: ");
                    String country = scanner.nextLine();
                    Author author = new Author(null, name, country);
                    authorDao.save(author);
                    System.out.println("Автор добавлен.");
                }
                case 2 -> {
                    List<Author> authors = authorDao.findAll();
                    authors.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Введите ID автора для удаления: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    authorDao.deleteById(id);
                    System.out.println("Автор удалён.");
                }
                case 4 -> {
                    System.out.print("Введите название книги: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите год публикации: ");
                    int year = scanner.nextInt();
                    System.out.print("Введите ID автора: ");
                    Long authorId = scanner.nextLong();
                    scanner.nextLine();
                    Book book = new Book(null, title, year, authorId);
                    bookDao.save(book);
                    System.out.println("Книга добавлена.");
                }
                case 5 -> {
                    List<Book> books = bookDao.findAll();
                    books.forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("Введите ID книги для удаления: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    bookDao.deleteById(id);
                    System.out.println("Книга удалена.");
                }
                case 7 -> {
                    System.out.print("Введите ID книги для обновления: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    Book existingBook;
                    try {
                        existingBook = bookDao.findById(id);
                    } catch (Exception e) {
                        System.out.println("Книга с таким ID не найдена.");
                        break;
                    }

                    System.out.print("Новое название (" + existingBook.getTitle() + "): ");
                    String title = scanner.nextLine();

                    System.out.print("Новый год публикации (" + existingBook.getPublishedYear() + "): ");
                    int year = scanner.nextInt();

                    System.out.print("Новый ID автора (" + existingBook.getAuthorId() + "): ");
                    Long authorId = scanner.nextLong();
                    scanner.nextLine();

                    Book updated = new Book(id, title, year, authorId);
                    bookDao.update(updated);
                    System.out.println("Книга обновлена.");

                }
                case 8 -> {
                    System.out.print("Введите ID автора для обновления: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    Author existingAuthor;
                    try {
                        existingAuthor = authorDao.findById(id);
                    } catch (Exception e) {
                        System.out.println("Автор с таким ID не найден.");
                        break;
                    }

                    System.out.print("Новое имя (" + existingAuthor.getName() + "): ");
                    String name = scanner.nextLine();

                    System.out.print("Новая страна (" + existingAuthor.getCountry() + "): ");
                    String country = scanner.nextLine();

                    Author updatedAuthor = new Author(id, name, country);
                    authorDao.update(updatedAuthor);
                    System.out.println("Автор обновлён.");
                }

                case 0 -> {
                    System.out.println("Выход из программы...");
                    context.close();
                    return;
                }
                default -> System.out.println("Неизвестная команда");
            }
        }
    }
}