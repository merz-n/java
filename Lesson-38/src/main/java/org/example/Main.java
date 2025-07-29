package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.example.dao.StudentDao;
import org.example.dao.StudentDaoImpl;
import org.example.entity.Student;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("✅ Подключение к БД прошло успешно!");
        } catch (Exception e) {
            System.out.println("❌ Ошибка подключения к БД:");
            e.printStackTrace();
        }
        StudentDao studentDao = new StudentDaoImpl();
        Scanner scanner = new Scanner(System.in);

        if (studentDao.findAll().isEmpty()) {

            studentDao.save(new Student("Anna Müller", "anna@example.com"));
            studentDao.save(new Student("Peter Schneider", "peter@example.com"));
            studentDao.save(new Student("Laura Becker", "laura@example.com"));
            studentDao.save(new Student("Tom Wagner", "tom@example.com"));
            studentDao.save(new Student("Julia Braun", "julia@example.com"));
            studentDao.save(new Student("Max Fischer", "max@example.com"));
        } else{
            System.out.println("Студенты уже есть в базе. Пропускаем автосоздание.");
        }

        boolean running = true;

        while (running) {
            System.out.println("""
                    Выбери действие:
                    1 - Показать всех студентов
                    2 - Добавить студента
                    3 - Удалить студента по ID
                    4 - Найти студента по ID
                    5 - Обновить студента по ID
                    0 - Выйти
                    """);

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    List<Student> students = studentDao.findAll();
                    students.forEach(System.out::println);
                }
                case "2" -> {
                    System.out.print("Имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    studentDao.save(new Student(name, email));
                }
                case "3" -> {
                    System.out.print("ID для удаления: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Student s = studentDao.findById(id);
                    if (s != null) studentDao.delete(s);
                    else System.out.println(" Студент не найден");
                }
                case "4" -> {
                    System.out.print("ID для поиска: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Student s = studentDao.findById(id);
                    System.out.println(s != null ? s : " Студент не найден");
                }
                case "5" -> {
                    System.out.print("ID для обновления: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Student s = studentDao.findById(id);
                    if (s != null) {
                        System.out.print("Новое имя: ");
                        s.setName(scanner.nextLine());
                        System.out.print("Новый email: ");
                        s.setEmail(scanner.nextLine());
                        studentDao.update(s);
                    } else System.out.println("Студент не найден");
                }
                case "0" -> {
                    running = false;
                    System.out.println("Программа завершена");
                }
                default -> System.out.println("Неизвестная команда");
            }
        }

        scanner.close();
    }
}