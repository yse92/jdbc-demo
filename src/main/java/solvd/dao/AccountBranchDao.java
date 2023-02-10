package solvd.dao;

import solvd.model.AccountBranch;

import java.sql.Connection;
import java.sql.SQLException;

public interface AccountBranchDao extends GenericDao<AccountBranch, Integer> {
    void updateByAccountID(AccountBranch entity, Integer accountID);
    void deleteByAccountID(Integer accountID, Connection connection);
    void deleteByAccountID(Integer accountID);
}
