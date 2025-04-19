package com.example.employeeproject.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.employeeproject.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeRepository {
    private EntityManager entityManager;

    public EmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    public Employee getSingleEmployee(int id) {
        return entityManager.find(Employee.class, id);
    }


    public Employee addEmployee(Employee employee) {
        Employee newEmployee = entityManager.merge(employee);
        return newEmployee;
    }

    public void delete(Employee employee) {
        entityManager.remove(employee);
    }


}
