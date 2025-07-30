package org.example.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.HibernateUtil;
import org.example.entity.Book;
import org.example.entity.BorrowedBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BorrowedBookRepository {

    private final Logger logger = LogManager.getLogger();
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public void create(BorrowedBook borrowedBook) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(borrowedBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            logger.catching(e);
            throw e;
        }
    }
    public void update(BorrowedBook borrowedBook) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(borrowedBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            logger.catching(e);
            throw e;
        }
    }

    public List<BorrowedBook> getAll() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from BorrowedBook", BorrowedBook.class).list();
        } catch (Exception e) {
            logger.catching(e);
            throw e;
        }
    }
    public BorrowedBook getById(Long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(BorrowedBook.class, id);
        } catch (Exception e) {
            logger.catching(e);
            throw e;
        }
    }

    public void remove(BorrowedBook borrowedBook) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(borrowedBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            logger.catching(e);
            throw e;
        }
    }
}
