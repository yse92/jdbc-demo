package solvd.dao;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;
import solvd.model.Employee;
import solvd.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao implements GenericDao<Transaction, Integer> {
    @Override
    public List<Transaction> getAll() {
        String selectAllCustomersQuery = "SELECT * FROM Transaction";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllCustomersQuery);
             ResultSet resultSet = statement.executeQuery()) {
            List<Transaction> transactions = new ArrayList<>();
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
            return transactions;
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public void update(Transaction entity, Integer id) {
        String updateTransactionQuery = "UPDATE Transaction SET amount = ? , transaction_date = ? , " +
                "transactionType_id = ?, account_id = ? , transactionErrorLog_id = ?, employee_id = ? WHERE id = ? ";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateTransactionQuery)) {
                statement.setDouble(1, entity.getAmount());
                statement.setDate(2, entity.getTransaction_date());
                statement.setInt(3, entity.getTransactionType_id());
                statement.setInt(4, entity.getAccount_id());
                statement.setInt(5, entity.getTransactionErrorLog_id());
                statement.setInt(6, entity.getEmployee_id());
                statement.setInt(7, id);
                statement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public Transaction getEntityById(Integer id) {
        String getTransactionQuery = "SELECT * FROM Transaction WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getTransactionQuery)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    Transaction transaction = new Transaction();
                    while(rs.next()) {
                        transaction.setAmount(rs.getDouble("amount"));
                        transaction.setTransaction_date(rs.getDate("transaction_date"));
                        transaction.setTransactionType_id(rs.getInt("transactionType_id"));
                        transaction.setTransactionErrorLog_id(rs.getInt("transactionErrorLog_id"));
                        transaction.setAccount_id(rs.getInt("account_id"));
                        transaction.setEmployee_id(rs.getInt("employee_id"));
                    }
                    return transaction;
                }
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteTransactionQuery = "DELETE FROM Transaction WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteTransactionQuery)) {
                statement.setInt(1, id);
                return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean insert(Transaction entity) {
        String insertQuery = "INSERT INTO Transaction " +
            "(amount, transaction_date, transactionType_id, transactionErrorLog_id, account_id, employee_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setDouble(1, entity.getAmount());
                statement.setDate(2, entity.getTransaction_date());
                statement.setInt(3, entity.getTransactionType_id());
                statement.setInt(4, entity.getTransactionErrorLog_id());
                statement.setInt(5, entity.getAccount_id());
                statement.setInt(6, entity.getEmployee_id());
                return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    public void deleteByTypeId(Integer id) {
        String deleteTransactionQuery = "DELETE FROM Transaction WHERE transactionType_id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteTransactionQuery)) {
                statement.setInt(1, id);
                statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }
}
