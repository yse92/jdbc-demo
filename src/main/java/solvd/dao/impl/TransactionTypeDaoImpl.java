package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.TransactionTypeDao;
import solvd.model.TransactionType;
import solvd.util.PermissionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static solvd.util.QueryCollection.*;

public class TransactionTypeDaoImpl implements TransactionTypeDao {
    Connection connection = CustomConnection.getInstance().getConnection();
    PreparedStatement statement;

    @Override
    public List<TransactionType> getAll() {
        List<TransactionType> transactionTypes = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_TRANSACTION_TYPES_QUERY);
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
        try {
            statement = connection.prepareStatement(UPDATE_TRANSACTION_TYPE_QUERY);
            statement.setString(1, entity.getDescription());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TransactionType getEntityById(Integer id) {
        TransactionType transactionType = new TransactionType();
        try {
            statement = connection.prepareStatement(GET_TRANSACTION_TYPE_BY_ID_QUERY);
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
        try {
            statement = connection.prepareStatement(DELETE_TRANSACTION_TYPE_QUERY);
            statement.setInt(1, id);
            PermissionUtil.setForeignKeyChecks(false, connection);
            new TransactionDaoImpl().deleteByTypeId(id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(TransactionType entity) {
        try {
            statement = connection.prepareStatement(INSERT_TRANSACTION_TYPE_QUERY);
            statement.setString(1, entity.getDescription());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
