package solvd.service.impl;

import solvd.dao.impl.CustomerDaoImpl;
import solvd.model.Customer;
import solvd.service.CustomerService;

import java.util.Collection;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDaoImpl customerDaoImpl;

    public CustomerServiceImpl() {
        customerDaoImpl = new CustomerDaoImpl();
    }

    @Override
    public boolean save(Customer entity) {
        return customerDaoImpl.insert(entity);
    }

    @Override
    public Collection<Customer> getAll() {
        return customerDaoImpl.getAll();
    }

    @Override
    public Customer getById(Integer id) {
        return customerDaoImpl.getEntityById(id);
    }

    @Override
    public void update(Customer newEntity, Integer id) {
        customerDaoImpl.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return customerDaoImpl.delete(id);
    }
}
