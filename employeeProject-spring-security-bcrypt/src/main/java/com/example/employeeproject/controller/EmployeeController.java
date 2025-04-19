package com.example.employeeproject.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.employeeproject.entity.Employee;
import com.example.employeeproject.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    public EmployeeController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.employeeService = employeeService;
    }


    // Add methods to handle HTTP requests here, e.g., GET, POST, PUT, DELETE
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<Employee> partialUpdateEmployee(@PathVariable int id, @RequestBody Map<String, Object> patchPayloads) {
        Employee tempEmployee = employeeService.getEmployeeById(id);

        if (tempEmployee == null) {
            return ResponseEntity.notFound().build();
        }

        if (patchPayloads.containsKey("id")) {
            throw new RuntimeException("ID not allowed to be updated");
        }

        Employee patchedEmployee = applyPatchToEmployee(tempEmployee, patchPayloads);
        employeeService.saveEmployee(patchedEmployee);
        return ResponseEntity.ok(patchedEmployee);
    }


    private Employee applyPatchToEmployee(Employee tempEmployee, Map<String, Object> patchPayloads) {
        // Convert data to JSON object nodes
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        // Convert patch payloads to JSON object nodes
        ObjectNode patchNode = objectMapper.convertValue(patchPayloads, ObjectNode.class);

        // Apply the patch to the employee node
        employeeNode.setAll(patchNode);

        // Convert the patched node back to an Employee object
        return objectMapper.convertValue(employeeNode, Employee.class);
    }
    
}
