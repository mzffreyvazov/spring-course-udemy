package org.example.crudjpa.repository;

import org.example.crudjpa.entity.Student;

import java.util.List;

public interface StudentRepoInterface {
    void save(Student student);

    void findById(Integer id);

    List<Student> findALl();

    void delete(Integer id);

}
