package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.AccountBranchDao;
import solvd.model.AccountBranch;
import solvd.util.PermissionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static solvd.util.QueryCollection.*;

public class AccountBranchDaoImpl implements AccountBranchDao {
    Connection connection = CustomConnection.getInstance().getConnection();
    PreparedStatement statement;

    @Override
    public List<AccountBranch> getAll() {
        List<AccountBranch> accountBranches = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_FROM_ACCOUNT_BRANCH);
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_ACCOUNT_BRANCH);
            while (resultSet.next()) {
                accountBranches.add(new AccountBranch(
                        resultSet.getInt("accountID"),
                        resultSet.getInt("branchID")
                ));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountBranches;
    }

    @Override
    public void update(AccountBranch entity, Integer id) {
        setStatement(entity, id, UPDATE_ACCOUNT_BRANCH_QUERY);
    }

    @Override
    public void updateByAccountID(AccountBranch entity, Integer accountID) {
        setStatement(entity, accountID, UPDATE_ACCOUNT_BRANCH_BY_ACCOUNT_ID_QUERY);
    }

    @Override
    public AccountBranch getEntityById(Integer id) {
        AccountBranch accountBranch = new AccountBranch();
        try {
            statement = connection.prepareStatement(SELECT_FROM_ACCOUNT_BRANCH_WHERE_ID);
            ResultSet rs = statement.executeQuery();
            statement.setInt(1, id);
            while(rs.next()) {
                accountBranch.setAccountID(rs.getInt("accountID"));
                accountBranch.setBranchID(rs.getInt("branchID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountBranch;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            statement = connection.prepareStatement(DELETE_FROM_ACCOUNT_BRANCH_WHERE_ACCOUNT_ID);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteByAccountID(Integer accountID) {
        try {
            statement = connection.prepareStatement(DELETE_FROM_ACCOUNT_BRANCH_WHERE_ACCOUNT_ID);
            statement.setInt(1, accountID);
            PermissionUtil.setForeignKeyChecks(false, connection);
            statement.execute();
            PermissionUtil.setForeignKeyChecks(true, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(AccountBranch entity) {
        try {
            statement = connection.prepareStatement(INSERT_ACCOUNT_BRANCH_QUERY);
            statement.setInt(1, entity.getAccountID());
            statement.setInt(2, entity.getBranchID());
            PermissionUtil.setForeignKeyChecks(false, connection);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setStatement(AccountBranch entity, Integer id, String updateAccountBranchQuery) {
        try {
            statement = connection.prepareStatement(updateAccountBranchQuery);
            statement.setInt(1, entity.getAccountID());
            statement.setInt(2, entity.getBranchID());
            statement.setInt(3, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
