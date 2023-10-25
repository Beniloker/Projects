package com.example.employeemanagementsystem.Service;

import com.example.employeemanagementsystem.Model.Employee;
import com.example.employeemanagementsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;


    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Employee employeeUpdate = employeeRepository.findById(id).orElseThrow();
        employeeUpdate.setFirstName(employee.getFirstName());
        employeeUpdate.setLastName(employee.getLastName());
        employeeUpdate.setEmail(employee.getEmail());
        return employeeRepository.save(employeeUpdate);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
