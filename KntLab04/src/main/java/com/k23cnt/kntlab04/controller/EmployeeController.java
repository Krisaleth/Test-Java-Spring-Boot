package com.k23cnt.kntlab04.controller;

import com.k23cnt.kntlab04.dto.EmployeeDTO;
import com.k23cnt.kntlab04.entity.Employee;
import com.k23cnt.kntlab04.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> findALl() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.save(employeeDTO);
        return ResponseEntity.ok("Employee created successfully");
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {
        if (employeeService.findById(id) != null) {
            employeeService.save(employeeDTO);
            return ResponseEntity.ok("Employee with id " + id + " updated successfully");
        }
        return ResponseEntity.badRequest().body("Id is not correct");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok("Employee with id " + id + " deleted successfully");
    }
}
