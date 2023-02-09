package solvd.dao;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;
import solvd.model.Account;
import solvd.util.PermissionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static solvd.util.Converter.convertToBit;
import static solvd.util.PermissionUtil.setForeignKeyChecks;

public class AccountDao implements GenericDao<Account, Integer> {

    @Override
    public List<Account> getAll() {
        String selectAllAccountsQuery = "SELECT * FROM Account";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllAccountsQuery);
             ResultSet resultSet = statement.executeQuery()) {
            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()) {
                accounts.add(new Account(
                    resultSet.getDouble("balance"),
                    resultSet.getBoolean("isActive"),
                    resultSet.getInt("accountType_id"),
                    resultSet.getInt("login_id")
                ));
            }
            return accounts;
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public void update(Account entity, Integer id) {
        String updateLoginQuery = "UPDATE Account SET balance = ? , isActive = ?, accountType_id = ?," +
                " login_id = ? WHERE accountID = ? ";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateLoginQuery)) {
                statement.setDouble(1, entity.getBalance());
                statement.setBoolean(2, entity.isActive());
                statement.setInt(3, entity.getAccountType_id());
                statement.setInt(4, entity.getLogin_id());
                statement.setInt(5, id);
                setForeignKeyChecks(false, connection);
                statement.executeUpdate();
                setForeignKeyChecks(true, connection);
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public Account getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM Account WHERE accountID = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getEntityByIdQuery)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                Account account = new Account();
                while(rs.next()) {
                    account.setBalance(rs.getDouble("balance"));
                    account.setActive(rs.getBoolean("isActive"));
                    account.setAccountType_id(rs.getInt("accountType_id"));
                    account.setLogin_id(rs.getInt("login_id"));
                }
                return account;
            }
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteAccountQuery = "DELETE FROM Account WHERE accountID = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteAccountQuery)) {
            statement.setInt(1, id);
            new AccountBranchDao().deleteByAccountID(id, connection);
            PermissionUtil.setForeignKeyChecks(false, connection);
            return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean insert(Account entity) {
        String insertQuery = "INSERT INTO Account (balance, isActive, accountType_id, login_id) VALUES (?,?,?,?)";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setDouble(1, entity.getBalance());
            statement.setBoolean(2, entity.isActive());
            statement.setInt(3, entity.getAccountType_id());
            statement.setInt(4, entity.getLogin_id());
            PermissionUtil.setForeignKeyChecks(false, connection);
            return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }
}
