package ru.etc.mvd.gismu.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.etc.mvd.gismu.test.persistence.entity.Employee;
import ru.etc.mvd.gismu.test.service.EmployeeService;

@RestController
@RequestMapping(path = "employees")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(produces = "application/json")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return ResponseEntity.ok("Inserted successfully");
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Employee getEmployeeById(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok("Updated successfully");
    }

}
