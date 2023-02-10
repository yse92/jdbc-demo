package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.AccountDao;
import solvd.dao.GenericDao;
import solvd.dao.impl.AccountBranchDaoImpl;
import solvd.exception.CustomException;
import solvd.model.Account;
import solvd.util.PermissionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static solvd.util.PermissionUtil.setForeignKeyChecks;

public class AccountDaoImpl extends CustomConnection implements AccountDao {
    PreparedStatement statement;

    @Override
    public List<Account> getAll() {
        String selectAllAccountsQuery = "SELECT * FROM Account";
        List<Account> accounts = new ArrayList<>();
        try {
            statement = getPrepareStatement(selectAllAccountsQuery);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                accounts.add(new Account(
                        resultSet.getDouble("balance"),
                        resultSet.getBoolean("isActive"),
                        resultSet.getInt("accountType_id"),
                        resultSet.getInt("login_id")
                ));
            }
            closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void update(Account entity, Integer id) {
        String updateLoginQuery = "UPDATE Account SET balance = ? , isActive = ?, accountType_id = ?," +
            " login_id = ? WHERE accountID = ? ";
        try {
            statement = getPrepareStatement(updateLoginQuery);
            statement.setDouble(1, entity.getBalance());
            statement.setBoolean(2, entity.isActive());
            statement.setInt(3, entity.getAccountType_id());
            statement.setInt(4, entity.getLogin_id());
            statement.setInt(5, id);
            setForeignKeyChecks(false, getConnection());
            statement.executeUpdate();
            setForeignKeyChecks(true, getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM Account WHERE accountID = ?";
        Account account = new Account();
        try {
            statement = getPrepareStatement(getEntityByIdQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                account.setBalance(rs.getDouble("balance"));
                account.setActive(rs.getBoolean("isActive"));
                account.setAccountType_id(rs.getInt("accountType_id"));
                account.setLogin_id(rs.getInt("login_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public boolean delete(Integer id) {
        String deleteAccountQuery = "DELETE FROM Account WHERE accountID = ?";
        try {
            statement = getPrepareStatement(deleteAccountQuery);
            statement.setInt(1, id);
            new AccountBranchDaoImpl().deleteByAccountID(id, getConnection());
            PermissionUtil.setForeignKeyChecks(false, getConnection());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Account entity) {
        String insertQuery = "INSERT INTO Account (balance, isActive, accountType_id, login_id) VALUES (?,?,?,?)";
        try {
            statement = getPrepareStatement(insertQuery);
            statement.setDouble(1, entity.getBalance());
            statement.setBoolean(2, entity.isActive());
            statement.setInt(3, entity.getAccountType_id());
            statement.setInt(4, entity.getLogin_id());
            PermissionUtil.setForeignKeyChecks(false, getConnection());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
