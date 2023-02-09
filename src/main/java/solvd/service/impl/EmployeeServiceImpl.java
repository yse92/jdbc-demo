package solvd.service.impl;

import solvd.dao.EmployeeDao;
import solvd.model.Employee;
import solvd.service.EmployeeService;

import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
        employeeDao  = new EmployeeDao();
    }

    @Override
    public boolean save(Employee entity) {
        return employeeDao.insert(entity);
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Override
    public Employee getById(Integer id) {
        return employeeDao.getEntityById(id);
    }

    @Override
    public void update(Employee newEntity, Integer id) {
        employeeDao.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return employeeDao.delete(id);
    }
}
