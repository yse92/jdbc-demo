package solvd.service.impl;

import solvd.dao.AccountDao;
import solvd.model.Account;
import solvd.service.AccountService;

import java.util.Collection;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public AccountServiceImpl() {
        accountDao = new AccountDao();
    }

    @Override
    public boolean save(Account entity) {
        return accountDao.insert(entity);
    }

    @Override
    public Collection<Account> getAll() {
        return accountDao.getAll();
    }

    @Override
    public Account getById(Integer id) {
        return accountDao.getEntityById(id);
    }

    @Override
    public void update(Account newEntity, Integer id) {
        accountDao.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return accountDao.delete(id);
    }
}
