package ru.etc.mvd.gismu.test.persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.etc.mvd.gismu.test.persistence.entity.Employee;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDao {

    @Autowired
    SessionFactory sessionFactory;


    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM EMPLOYEE";
        List<Employee> employees = session.createSQLQuery(sql).getResultList();
        session.getTransaction().commit();
        session.close();
        return employees;
    }

    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "insert into employee(firstName, lastName, email) values (?,?,?)";
        session.createSQLQuery(sql).setParameter(1, employee.getFirstName()).
                setParameter(2, employee.getLastName()).
                setParameter(3, employee.getEmail()).
                executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Employee employee = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            String sql = "SELECT * FROM employee WHERE ID = ?";
            employee = session.createNativeQuery(sql, Employee.class).setParameter(1, id).getSingleResult();
        } catch (NoResultException e) {
            return employee;
        }
        session.getTransaction().commit();
        session.close();
        return employee;
    }

    @Override
    public void deleteEmployee(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "DELETE FROM employee WHERE ID = ?";
        session.createNativeQuery(sql, Employee.class).setParameter(1, id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "UPDATE employee SET firstName = ?, lastName = ?," +
                " email = ? where ID = ?";
        session.createNativeQuery(sql, Employee.class).setParameter(1, employee.getFirstName())
                .setParameter(2, employee.getLastName()).setParameter(3, employee.getEmail())
                .setParameter(4, id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return employee;
    }
}
