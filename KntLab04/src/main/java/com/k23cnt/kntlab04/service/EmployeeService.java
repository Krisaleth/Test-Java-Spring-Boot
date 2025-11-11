package com.k23cnt.kntlab04.service;

import com.k23cnt.kntlab04.dto.EmployeeDTO;
import com.k23cnt.kntlab04.entity.Employee;
import com.k23cnt.kntlab04.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFullName(employeeDTO.getFullName());
        employee.setGender(employeeDTO.getGender());
        employee.setAge(employeeDTO.getAge());
        employee.setSalary(employeeDTO.getSalary());
        return employee;
    }

    public Employee save(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        return employeeRepository.save(employee);
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }
}
