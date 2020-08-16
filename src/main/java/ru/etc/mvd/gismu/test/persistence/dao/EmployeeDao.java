package ru.etc.mvd.gismu.test.persistence.dao;

import ru.etc.mvd.gismu.test.persistence.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployeeById(long id);

    void deleteEmployee(long id);

    Employee updateEmployee(long id,Employee employee);
}
