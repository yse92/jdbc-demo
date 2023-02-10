package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.AccountDao;
import solvd.model.Account;
import solvd.util.PermissionUtil;
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
                accounts.add(getAccountFromResultSet(resultSet));
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
            setStatement(statement, entity);
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
                account = getAccountFromResultSet(rs);
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
            setStatement(statement, entity);
            PermissionUtil.setForeignKeyChecks(false, getConnection());
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
