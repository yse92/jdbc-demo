package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.TransactionTypeDao;
import solvd.dao.impl.TransactionDaoImpl;
import solvd.exception.CustomException;
import solvd.model.TransactionType;
import solvd.util.PermissionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionTypeDaoImpl extends CustomConnection implements TransactionTypeDao {
    PreparedStatement statement;

    @Override
    public List<TransactionType> getAll() {
        String selectAllTransactionTypesQuery = "SELECT * FROM TransactionType";
        List<TransactionType> transactionTypes = new ArrayList<>();
        try {
            statement = getPrepareStatement(selectAllTransactionTypesQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transactionTypes.add(new TransactionType(
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionTypes;
    }

    @Override
    public void update(TransactionType entity, Integer id) {
        String updateTransactionTypeQuery = "UPDATE TransactionType SET description = ? WHERE id = ? ";
        try {
            statement = getPrepareStatement(updateTransactionTypeQuery);
            statement.setString(1, entity.getDescription());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TransactionType getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM TransactionType WHERE id = ?";
        TransactionType transactionType = new TransactionType();
        try {
            statement = getPrepareStatement(getEntityByIdQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                transactionType.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionType;
    }

    @Override
    public boolean delete(Integer id) {
        String deleteTypeQuery = "DELETE FROM TransactionType WHERE id = ?";
        try {
            statement = getPrepareStatement(deleteTypeQuery);
            statement.setInt(1, id);
            PermissionUtil.setForeignKeyChecks(false, getConnection());
            new TransactionDaoImpl().deleteByTypeId(id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(TransactionType entity) {
        String insertQuery = "INSERT INTO TransactionType (description) VALUES (?)";
        try {
            statement = getPrepareStatement(insertQuery);
            statement.setString(1, entity.getDescription());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
