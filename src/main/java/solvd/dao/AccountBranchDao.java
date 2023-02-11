package solvd.dao;

import solvd.model.AccountBranch;

public interface AccountBranchDao extends GenericDao<AccountBranch, Integer> {
    void updateByAccountID(AccountBranch entity, Integer accountID);
    void deleteByAccountID(Integer accountID);
}
