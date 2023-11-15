package com.example.exercise2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(int id, Employee employee) {
        Optional<Employee> empToUpdate = employeeRepository.findById(id);

        if (empToUpdate.isPresent()) {
            employee.setId(id);
            return employeeRepository.save(employee);
        }else{
            return null;
        }
    }

    public void deleteEmployee(int id) {
        Optional<Employee> empToDelete = employeeRepository.findById(id);

        if (empToDelete.isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            return;
        }
    }
}
