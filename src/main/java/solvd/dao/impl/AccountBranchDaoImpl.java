package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.AccountBranchDao;
import solvd.exception.CustomException;
import solvd.model.AccountBranch;
import solvd.util.PermissionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountBranchDaoImpl extends CustomConnection implements AccountBranchDao {
    PreparedStatement statement;

    @Override
    public List<AccountBranch> getAll() {
        String selectAllAccountBranchQuery = "SELECT * FROM AccountBranch";
        List<AccountBranch> accountBranches = new ArrayList<>();
        try {
            statement = getPrepareStatement(selectAllAccountBranchQuery);
            ResultSet resultSet = statement.executeQuery(selectAllAccountBranchQuery);
            while (resultSet.next()) {
                accountBranches.add(new AccountBranch(
                        resultSet.getInt("accountID"),
                        resultSet.getInt("branchID")
                ));
            }
            closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountBranches;
    }

    @Override
    public void update(AccountBranch entity, Integer id) {
        String updateAccountBranchQuery = "UPDATE AccountBranch SET accountID = ? , branchID = ? WHERE id = ?";
        try {
            statement = getPrepareStatement(updateAccountBranchQuery);
            statement.setInt(1, entity.getAccountID());
            statement.setInt(2, entity.getBranchID());
            statement.setInt(3, id);
            statement.executeUpdate();
            closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateByAccountID(AccountBranch entity, Integer accountID) {
        String updateAccountBranchByAccountIDQuery = "UPDATE AccountBranch SET accountID = ? , " +
                "branchID = ? WHERE accountID = ?";
        try {
            statement = getPrepareStatement(updateAccountBranchByAccountIDQuery);
            statement.setInt(1, entity.getAccountID());
            statement.setInt(2, entity.getBranchID());
            statement.setInt(3, accountID);
            statement.executeUpdate();
            closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccountBranch getEntityById(Integer id) {
        String getCustomerQuery = "SELECT * FROM AccountBranch WHERE id = ?";
        AccountBranch accountBranch = new AccountBranch();
        try {
            statement = getPrepareStatement(getCustomerQuery);
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
        String deleteAccountBranchQuery = "DELETE FROM AccountBranch WHERE id = ?";
        try {
            statement = getPrepareStatement(deleteAccountBranchQuery);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteByAccountID(Integer accountID, Connection connection) {
        String deleteAccountBranchQuery = "DELETE FROM AccountBranch WHERE accountID = ?";
        try {
            statement = getPrepareStatement(deleteAccountBranchQuery);
            statement.setInt(1, accountID);
            PermissionUtil.setForeignKeyChecks(false, connection);
            statement.execute();
            PermissionUtil.setForeignKeyChecks(true, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByAccountID(Integer accountID) {
        String deleteAccountBranchQuery = "DELETE FROM AccountBranch WHERE accountID = ?";
        statement = getPrepareStatement(deleteAccountBranchQuery);
        try {
            statement.setInt(1, accountID);
            PermissionUtil.setForeignKeyChecks(false, getConnection());
            statement.execute();
            PermissionUtil.setForeignKeyChecks(true, getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(AccountBranch entity) {
        String insertQuery = "INSERT INTO AccountBranch (accountID, branchID) VALUES (?,?)";
        try {
            statement = getPrepareStatement(insertQuery);
            statement.setInt(1, entity.getAccountID());
            statement.setInt(2, entity.getBranchID());
            PermissionUtil.setForeignKeyChecks(false, getConnection());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
