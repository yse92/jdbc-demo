package solvd.dao;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;
import solvd.model.AccountBranch;
import solvd.model.Customer;
import solvd.util.PermissionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountBranchDao implements GenericDao<AccountBranch, Integer>{

    @Override
    public List<AccountBranch> getAll() {
        String selectAllAccountBranchQuery = "SELECT * FROM AccountBranch";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllAccountBranchQuery);
             ResultSet resultSet = statement.executeQuery()) {
                List<AccountBranch> accountBranches = new ArrayList<>();
                while (resultSet.next()) {
                    accountBranches.add(new AccountBranch(
                        resultSet.getInt("accountID"),
                        resultSet.getInt("branchID")
                    ));
                }
                return accountBranches;
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public void update(AccountBranch entity, Integer id) {
        String updateAccountBranchQuery = "UPDATE AccountBranch SET accountID = ? , branchID = ? WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateAccountBranchQuery)) {
                statement.setInt(1, entity.getAccountID());
                statement.setInt(2, entity.getBranchID());
                statement.setInt(3, id);
                statement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    public void updateByAccountID(AccountBranch entity, Integer accountID) {
        String updateAccountBranchByAccountIDQuery = "UPDATE AccountBranch SET accountID = ? , " +
                "branchID = ? WHERE accountID = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateAccountBranchByAccountIDQuery)) {
                statement.setInt(1, entity.getAccountID());
                statement.setInt(2, entity.getBranchID());
                statement.setInt(3, accountID);
                statement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public AccountBranch getEntityById(Integer id) {
        String getCustomerQuery = "SELECT * FROM AccountBranch WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getCustomerQuery)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    AccountBranch accountBranch = new AccountBranch();
                    while(rs.next()) {
                        accountBranch.setAccountID(rs.getInt("accountID"));
                        accountBranch.setBranchID(rs.getInt("branchID"));
                    }
                    return accountBranch;
                }
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteAccountBranchQuery = "DELETE FROM AccountBranch WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteAccountBranchQuery)) {
                statement.setInt(1, id);
                return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    public void deleteByAccountID(Integer accountID, Connection connection) {
        String deleteAccountBranchQuery = "DELETE FROM AccountBranch WHERE accountID = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteAccountBranchQuery)) {
            statement.setInt(1, accountID);
            PermissionUtil.setForeignKeyChecks(false, connection);
            statement.execute();
            PermissionUtil.setForeignKeyChecks(true, connection);
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    public void deleteByAccountID(Integer accountID) {
        String deleteAccountBranchQuery = "DELETE FROM AccountBranch WHERE accountID = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteAccountBranchQuery)) {
            statement.setInt(1, accountID);
            PermissionUtil.setForeignKeyChecks(false, connection);
            statement.execute();
            PermissionUtil.setForeignKeyChecks(true, connection);
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean insert(AccountBranch entity) {
        String insertQuery = "INSERT INTO AccountBranch (accountID, branchID) VALUES (?,?)";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setInt(1, entity.getAccountID());
                statement.setInt(2, entity.getBranchID());
                PermissionUtil.setForeignKeyChecks(false, connection);
            return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }
}
