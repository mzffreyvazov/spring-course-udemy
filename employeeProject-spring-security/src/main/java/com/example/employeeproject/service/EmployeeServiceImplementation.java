package com.example.employeeproject.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.employeeproject.entity.Employee;
import com.example.employeeproject.repository.EmployeeRepository;
import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.getSingleEmployee(id);
    }

    @Transactional
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.getSingleEmployee(id);
        if (employee != null) {
            employeeRepository.delete(employee);
        }
    }

    @Transactional
    @Override
    public Employee updateEmployee(int id, Employee employeeDetails) {
        Employee employee = employeeRepository.getSingleEmployee(id);
        if (employee != null) {
            employeeDetails.setId(id); // Ensure the ID is set correctly
            return employeeRepository.addEmployee(employeeDetails); // Using existing addEmployee which uses merge
        }
        return null;
    }

    // Implement other methods as needed, e.g., create, update, delete, find by ID
    
}
