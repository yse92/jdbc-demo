package solvd.service.impl;

import solvd.dao.impl.AccountBranchDaoImpl;
import solvd.model.AccountBranch;
import solvd.service.AccountBranchService;

import java.util.Collection;

public class AccountBranchServiceImpl implements AccountBranchService {

    private AccountBranchDaoImpl accountBranchDaoImpl;

    public AccountBranchServiceImpl() {
        accountBranchDaoImpl = new AccountBranchDaoImpl();
    }

    @Override
    public boolean save(AccountBranch entity) {
        return accountBranchDaoImpl.insert(entity);
    }

    @Override
    public Collection<AccountBranch> getAll() {
        return accountBranchDaoImpl.getAll();
    }

    @Override
    public AccountBranch getById(Integer id) {
        return accountBranchDaoImpl.getEntityById(id);
    }

    @Override
    public void update(AccountBranch newEntity, Integer id) {
        accountBranchDaoImpl.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return accountBranchDaoImpl.delete(id);
    }

    @Override
    public void deleteByAccountID(Integer accountID) {
        accountBranchDaoImpl.deleteByAccountID(accountID);
    }

    @Override
    public void updateByAccountID(AccountBranch accountBranch, Integer accountID) {
        accountBranchDaoImpl.updateByAccountID(accountBranch, accountID);
    }

    @Override
    public boolean save(Integer accountID, Integer branchID) {
        return accountBranchDaoImpl.insert(new AccountBranch(accountID, branchID));
    }
}
