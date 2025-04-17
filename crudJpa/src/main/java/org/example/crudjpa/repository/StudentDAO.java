package org.example.crudjpa.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.crudjpa.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAO implements StudentRepoInterface {

    private EntityManager entityManager;

    @Autowired
    public StudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public void findById(Integer id) {
        entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findALl() {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = entityManager.find(Student.class, id);
        //int rows = entityManager.createQuery("delete from Student s where s.id = :id").executeUpdate();
        entityManager.remove(student);
    }

}
