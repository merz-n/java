package org.example.dao;

import org.example.entity.Student;

import java.util.List;

public interface StudentDao {
    void save(Student student);
    Student findById(int id);
    List<Student> findAll();
    void update(Student student);
    void delete(Student student);
}
