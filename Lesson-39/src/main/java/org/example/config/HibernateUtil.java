package org.example.config;

import java.util.Properties;

import org.example.entity.Book;
import org.example.entity.BorrowedBook;
import org.example.entity.Reader;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/library");
            configuration.setProperty("hibernate.connection.username", "admin");
            configuration.setProperty("hibernate.connection.password", "admin");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
            configuration.setProperty("hibernate.transaction.coordinator_class", "jdbc");
            configuration.setProperty("hibernate.current_session_context_class", "thread");
            configuration.addAnnotatedClass(Book.class);
            configuration.addAnnotatedClass(Reader.class);
            configuration.addAnnotatedClass(BorrowedBook.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;

    }
}
