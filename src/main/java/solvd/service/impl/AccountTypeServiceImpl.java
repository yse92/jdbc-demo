package solvd.service.impl;

import solvd.dao.impl.AccountTypeDaoImpl;
import solvd.model.AccountType;
import solvd.service.AccountTypeService;

import java.util.Collection;

public class AccountTypeServiceImpl implements AccountTypeService {
    private AccountTypeDaoImpl accountTypeDaoImpl;

    public AccountTypeServiceImpl() {
        accountTypeDaoImpl = new AccountTypeDaoImpl();
    }

    @Override
    public boolean save(AccountType entity) {
        return accountTypeDaoImpl.insert(entity);
    }

    @Override
    public Collection<AccountType> getAll() {
        return accountTypeDaoImpl.getAll();
    }

    @Override
    public AccountType getById(Integer id) {
        return accountTypeDaoImpl.getEntityById(id);
    }

    @Override
    public void update(AccountType newEntity, Integer id) {
        accountTypeDaoImpl.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return accountTypeDaoImpl.delete(id);
    }
}
