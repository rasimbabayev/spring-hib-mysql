package ru.etc.mvd.gismu.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.etc.mvd.gismu.test.persistence.dao.EmployeeDao;
import ru.etc.mvd.gismu.test.persistence.entity.Employee;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public List<Employee> getEmployees() {
        return employeeDao.getAllEmployees();
    }

    public void createEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

    public Employee getEmployeeById(long id) {
        Employee dbEmployee = employeeDao.getEmployeeById(id);
        if (dbEmployee == null) throw new RuntimeException("Employee not found");
        return employeeDao.getEmployeeById(id);
    }

    public void deleteEmployeeById(long id) {
        employeeDao.deleteEmployee(id);
    }

    public void updateEmployee(long id, Employee employee) {
        Employee dbEmployee = employeeDao.getEmployeeById(id);
        if (dbEmployee == null) throw new RuntimeException("Employee not found");
        employeeDao.updateEmployee(id, employee);
    }
}
