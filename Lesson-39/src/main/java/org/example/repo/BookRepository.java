package org.example.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.HibernateUtil;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookRepository {

    private final Logger logger = LogManager.getLogger();
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void create(Book book) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            logger.catching(e);
            throw e;
        }
    }

    public void update(Book book) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            logger.catching(e);
            throw e;
        }
    }

    public List<Book> getAll() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Book", Book.class).list();
        } catch (Exception e) {
            logger.catching(e);
            throw e;
        }
    }

    public Book getById(Long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Book.class, id);
        } catch (Exception e) {
            logger.catching(e);
            throw e;
        }
    }

    public void remove(Book book) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            logger.catching(e);
            throw e;
        }
    }
}
