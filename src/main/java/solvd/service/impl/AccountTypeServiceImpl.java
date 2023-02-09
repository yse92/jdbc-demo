package solvd.service.impl;

import solvd.dao.AccountTypeDao;
import solvd.model.AccountType;
import solvd.service.AccountTypeService;

import java.util.Collection;

public class AccountTypeServiceImpl implements AccountTypeService {
    private AccountTypeDao accountTypeDao;

    public AccountTypeServiceImpl() {
        accountTypeDao  = new AccountTypeDao();
    }

    @Override
    public boolean save(AccountType entity) {
        return accountTypeDao.insert(entity);
    }

    @Override
    public Collection<AccountType> getAll() {
        return accountTypeDao.getAll();
    }

    @Override
    public AccountType getById(Integer id) {
        return accountTypeDao.getEntityById(id);
    }

    @Override
    public void update(AccountType newEntity, Integer id) {
        accountTypeDao.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return accountTypeDao.delete(id);
    }
}
