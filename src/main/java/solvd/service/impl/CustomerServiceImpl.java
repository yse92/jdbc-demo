package solvd.service.impl;

import solvd.dao.CustomerDao;
import solvd.model.Customer;
import solvd.service.CustomerService;

import java.util.Collection;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public CustomerServiceImpl() {
        customerDao  = new CustomerDao();
    }

    @Override
    public boolean save(Customer entity) {
        return customerDao.insert(entity);
    }

    @Override
    public Collection<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public Customer getById(Integer id) {
        return customerDao.getEntityById(id);
    }

    @Override
    public void update(Customer newEntity, Integer id) {
        customerDao.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return customerDao.delete(id);
    }
}
