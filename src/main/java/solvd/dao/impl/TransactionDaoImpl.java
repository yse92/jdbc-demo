package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.TransactionDao;
import solvd.exception.CustomException;
import solvd.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl extends CustomConnection implements TransactionDao {
    PreparedStatement statement;

    @Override
    public List<Transaction> getAll() {
        String selectAllCustomersQuery = "SELECT * FROM Transaction";
        List<Transaction> transactions = new ArrayList<>();
        try {
            statement = getPrepareStatement(selectAllCustomersQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transactions.add(new Transaction(
                        resultSet.getDouble("amount"),
                        resultSet.getDate("transaction_date"),
                        resultSet.getInt("transactionType_id"),
                        resultSet.getInt("account_id"),
                        resultSet.getInt("transactionErrorLog_id"),
                        resultSet.getInt("employee_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void update(Transaction entity, Integer id) {
        String updateTransactionQuery = "UPDATE Transaction SET amount = ? , transaction_date = ? , " +
        "transactionType_id = ?, account_id = ? , transactionErrorLog_id = ?, employee_id = ? WHERE id = ? ";
        try {
            statement = getPrepareStatement(updateTransactionQuery);
            statement.setDouble(1, entity.getAmount());
            statement.setDate(2, entity.getTransaction_date());
            statement.setInt(3, entity.getTransactionType_id());
            statement.setInt(4, entity.getAccount_id());
            statement.setInt(5, entity.getTransactionErrorLog_id());
            statement.setInt(6, entity.getEmployee_id());
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction getEntityById(Integer id) {
        String getTransactionQuery = "SELECT * FROM Transaction WHERE id = ?";
        Transaction transaction = new Transaction();
        try {
            statement = getPrepareStatement(getTransactionQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransaction_date(rs.getDate("transaction_date"));
                transaction.setTransactionType_id(rs.getInt("transactionType_id"));
                transaction.setTransactionErrorLog_id(rs.getInt("transactionErrorLog_id"));
                transaction.setAccount_id(rs.getInt("account_id"));
                transaction.setEmployee_id(rs.getInt("employee_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    @Override
    public boolean delete(Integer id) {
        String deleteTransactionQuery = "DELETE FROM Transaction WHERE id = ?";
        try {
            statement = getPrepareStatement(deleteTransactionQuery);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Transaction entity) {
        String insertQuery = "INSERT INTO Transaction " +
        "(amount, transaction_date, transactionType_id, transactionErrorLog_id, account_id, employee_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            statement = getPrepareStatement(insertQuery);
            statement.setDouble(1, entity.getAmount());
            statement.setDate(2, entity.getTransaction_date());
            statement.setInt(3, entity.getTransactionType_id());
            statement.setInt(4, entity.getTransactionErrorLog_id());
            statement.setInt(5, entity.getAccount_id());
            statement.setInt(6, entity.getEmployee_id());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteByTypeId(Integer id) {
        String deleteTransactionQuery = "DELETE FROM Transaction WHERE transactionType_id = ?";
        try {
            statement = getPrepareStatement(deleteTransactionQuery);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
