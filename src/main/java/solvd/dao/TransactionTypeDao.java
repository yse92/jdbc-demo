package solvd.dao;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;
import solvd.model.AccountType;
import solvd.model.Transaction;
import solvd.model.TransactionType;
import solvd.util.PermissionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionTypeDao implements GenericDao<TransactionType, Integer> {

    @Override
    public List<TransactionType> getAll() {
        String selectAllTransactionTypesQuery = "SELECT * FROM TransactionType";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllTransactionTypesQuery);
             ResultSet resultSet = statement.executeQuery()) {
                List<TransactionType> transactionTypes = new ArrayList<>();
                while (resultSet.next()) {
                    transactionTypes.add(new TransactionType(
                            resultSet.getString("description")
                    ));
                }
                return transactionTypes;
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public void update(TransactionType entity, Integer id) {
        String updateTransactionTypeQuery = "UPDATE TransactionType SET description = ? WHERE id = ? ";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateTransactionTypeQuery)) {
                statement.setString(1, entity.getDescription());
                statement.setInt(2, id);
                statement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public TransactionType getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM TransactionType WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getEntityByIdQuery)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    TransactionType transactionType = new TransactionType();
                    while(rs.next()) {
                        transactionType.setDescription(rs.getString("description"));
                    }
                    return transactionType;
                }
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteTypeQuery = "DELETE FROM TransactionType WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteTypeQuery)) {
                statement.setInt(1, id);
                PermissionUtil.setForeignKeyChecks(false, connection);
                new TransactionDao().deleteByTypeId(id);
                return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean insert(TransactionType entity) {
        String insertQuery = "INSERT INTO TransactionType (description) VALUES (?)";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, entity.getDescription());
                return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }
}
