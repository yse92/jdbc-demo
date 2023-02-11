package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.AccountDao;
import solvd.model.Account;
import solvd.util.PermissionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static solvd.util.PermissionUtil.setForeignKeyChecks;
import static solvd.util.QueryCollection.*;

public class AccountDaoImpl implements AccountDao {
    Connection connection = CustomConnection.getInstance().getConnection();
    PreparedStatement statement;

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            statement = connection.prepareStatement(selectAllAccountsQuery);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                accounts.add(getAccountFromResultSet(resultSet));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void update(Account entity, Integer id) {
        try {
            statement = connection.prepareStatement(updateAccountQuery);
            setStatement(statement, entity);
            statement.setInt(5, id);
            setForeignKeyChecks(false, connection);
            statement.executeUpdate();
            setForeignKeyChecks(true, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getEntityById(Integer id) {
        Account account = new Account();
        try {
            statement = connection.prepareStatement(getAccountByIdQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                account = getAccountFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            statement = connection.prepareStatement(deleteAccountQuery);
            statement.setInt(1, id);
            new AccountBranchDaoImpl().deleteByAccountID(id);
            PermissionUtil.setForeignKeyChecks(false, connection);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Account entity) {
        try {
            statement = connection.prepareStatement(insertAccountQuery);
            setStatement(statement, entity);
            PermissionUtil.setForeignKeyChecks(false, connection);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Account getAccountFromResultSet(ResultSet resultSet) throws SQLException {
        double balance = resultSet.getDouble("balance");
        boolean isActive = resultSet.getBoolean("isActive");
        int accountType_id = resultSet.getInt("accountType_id");
        int login_id = resultSet.getInt("login_id");
        return new Account(balance, isActive, accountType_id, login_id);
    }

    private void setStatement(PreparedStatement statement, Account account) {
        try {
            statement.setDouble(1, account.getBalance());
            statement.setBoolean(2, account.getIsActive());
            statement.setInt(3, account.getAccountType_id());
            statement.setInt(4, account.getLogin_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
