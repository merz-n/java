package org.example.repo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.HibernateUtil;
import org.example.entity.Reader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class ReaderRepository {

    private final Logger logger = LogManager.getLogger();
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public void create(Reader reader) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(reader);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            logger.catching(e);
            throw e;
        }
    }
    public void update(Reader reader) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(reader);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            logger.catching(e);
            throw e;
        }
    }

    public List<Reader> getAll() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Reader", Reader.class).list();
        } catch (Exception e) {
            logger.catching(e);
            throw e;
        }
    }
    public Reader getById(Long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Reader.class, id);
        } catch (Exception e) {
            logger.catching(e);
            throw e;
        }
    }

    public void remove(Reader reader) throws Exception {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(reader);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            logger.catching(e);
            throw e;
        }
    }
}
