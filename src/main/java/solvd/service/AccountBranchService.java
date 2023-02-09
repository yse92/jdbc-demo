package solvd.service;

import solvd.model.AccountBranch;

public interface AccountBranchService extends Service<AccountBranch, Integer> {
    void deleteByAccountID(Integer accountID);
    void updateByAccountID(AccountBranch accountBranch, Integer accountID);
    boolean save(Integer accountID, Integer branchID);
}
