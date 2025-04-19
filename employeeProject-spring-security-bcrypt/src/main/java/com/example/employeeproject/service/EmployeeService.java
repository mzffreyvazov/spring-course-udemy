package com.example.employeeproject.service;

import java.util.List;

import com.example.employeeproject.entity.Employee;

public interface EmployeeService {
    // Define methods for employee-related operations here, e.g., create, update, delete, find by ID
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    void saveEmployee(Employee employee);
    void deleteEmployee(int id);
    Employee updateEmployee(int id, Employee employee);
    // Employee partialUpdatEmployee(int id, Employee employee);
}
