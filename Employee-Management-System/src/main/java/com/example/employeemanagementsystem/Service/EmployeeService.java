package com.example.employeemanagementsystem.Service;

import com.example.employeemanagementsystem.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Optional<Employee> getEmployeeById(int id);
    List<Employee> getAllEmployee();
    Employee updateEmployee(int id, Employee employee);
    void deleteEmployee(int id);
}
