package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.EmployeeDao;
import io.khasang.freefly.entity.Employee;
import io.khasang.freefly.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.create(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeDao.getById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getList();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeDao.deleteById(id);
    }
}
