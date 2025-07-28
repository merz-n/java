package org.example;

import java.util.Properties;

import org.example.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/students_hibernate");
            configuration.setProperty("hibernate.connection.username", "admin");
            configuration.setProperty("hibernate.connection.password", "admin");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
            configuration.addAnnotatedClass(Student.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;

    }
}
