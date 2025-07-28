package org.example.dao;

import org.example.HibernateUtil;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao{

    @Override
    public void save(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            System.out.println("✅ Студент добавлен: " + student);
        } catch (Exception e) {
            if(transaction !=null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(int id) {
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            student = session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            students = session.createQuery("FROM Student", Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
            System.out.println("Студент обновлён: " + student);
        } catch (Exception e) {
            if(transaction !=null){
                transaction.rollback();
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
            System.out.println("✅ Студент удалён: " + student);
        } catch (Exception e) {
            if(transaction !=null){
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}
