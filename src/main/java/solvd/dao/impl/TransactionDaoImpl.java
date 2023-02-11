package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.TransactionDao;
import solvd.model.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static solvd.util.QueryCollection.*;

public class TransactionDaoImpl implements TransactionDao {
    Connection connection = CustomConnection.getInstance().getConnection();
    PreparedStatement statement;

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            statement = connection.prepareStatement(selectAllTransactionsQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transactions.add(getTransactionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void update(Transaction entity, Integer id) {
        try {
            statement = connection.prepareStatement(updateTransactionQuery);
            setStatement(statement, entity);
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction getEntityById(Integer id) {
        Transaction transaction = new Transaction();
        try {
            statement = connection.prepareStatement(getTransactionQuery);
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
        try {
            statement = connection.prepareStatement(deleteTransactionQuery);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Transaction entity) {
        try {
            statement = connection.prepareStatement(insertTransactionQuery);
            setStatement(statement, entity);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteByTypeId(Integer id) {
        try {
            statement = connection.prepareStatement(deleteTransactionByTypeIdQuery);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Transaction getTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        double amount = resultSet.getDouble("amount");
        Date transaction_date = resultSet.getDate("transaction_date");
        int transactionType_id = resultSet.getInt("transactionType_id");
        int account_id = resultSet.getInt("account_id");
        int transactionErrorLog_id = resultSet.getInt("transactionErrorLog_id");
        int employee_id = resultSet.getInt("employee_id");
        return new Transaction(amount, transaction_date, transactionType_id, transactionErrorLog_id, account_id, employee_id);
    }

    private void setStatement(PreparedStatement statement, Transaction transaction) {
        try {
            statement.setDouble(1, transaction.getAmount());
            statement.setDate(2, transaction.getTransaction_date());
            statement.setInt(3, transaction.getTransactionType_id());
            statement.setInt(4, transaction.getAccount_id());
            statement.setInt(5, transaction.getTransactionErrorLog_id());
            statement.setInt(6, transaction.getEmployee_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
