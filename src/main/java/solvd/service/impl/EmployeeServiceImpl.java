package solvd.service.impl;

import solvd.dao.impl.EmployeeDaoImpl;
import solvd.model.Employee;
import solvd.service.EmployeeService;

import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDaoImpl employeeDaoImpl;

    public EmployeeServiceImpl() {
        employeeDaoImpl = new EmployeeDaoImpl();
    }

    @Override
    public boolean save(Employee entity) {
        return employeeDaoImpl.insert(entity);
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeDaoImpl.getAll();
    }

    @Override
    public Employee getById(Integer id) {
        return employeeDaoImpl.getEntityById(id);
    }

    @Override
    public void update(Employee newEntity, Integer id) {
        employeeDaoImpl.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return employeeDaoImpl.delete(id);
    }
}
