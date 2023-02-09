package solvd.service.impl;

import solvd.dao.AccountBranchDao;
import solvd.model.AccountBranch;
import solvd.service.AccountBranchService;

import java.util.Collection;

public class AccountBranchServiceImpl implements AccountBranchService {

    private AccountBranchDao accountBranchDao;

    public AccountBranchServiceImpl() {
        accountBranchDao = new AccountBranchDao();
    }

    @Override
    public boolean save(AccountBranch entity) {
        return accountBranchDao.insert(entity);
    }

    @Override
    public Collection<AccountBranch> getAll() {
        return accountBranchDao.getAll();
    }

    @Override
    public AccountBranch getById(Integer id) {
        return accountBranchDao.getEntityById(id);
    }

    @Override
    public void update(AccountBranch newEntity, Integer id) {
        accountBranchDao.update(newEntity, id);
    }

    @Override
    public boolean delete(Integer id) {
        return accountBranchDao.delete(id);
    }

    @Override
    public void deleteByAccountID(Integer accountID) {
        accountBranchDao.deleteByAccountID(accountID);
    }

    @Override
    public void updateByAccountID(AccountBranch accountBranch, Integer accountID) {
        accountBranchDao.updateByAccountID(accountBranch, accountID);
    }

    @Override
    public boolean save(Integer accountID, Integer branchID) {
        return accountBranchDao.insert(new AccountBranch(accountID, branchID));
    }
}
