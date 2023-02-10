package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.AccountTypeDao;
import solvd.model.AccountType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountTypeDaoImpl extends CustomConnection implements AccountTypeDao {
    PreparedStatement statement;

    @Override
    public List<AccountType> getAll() {
        String selectAllTypesQuery = "SELECT * FROM AccountType";
        List<AccountType> accountTypes = new ArrayList<>();
        try {
            statement = getPrepareStatement(selectAllTypesQuery);
            ResultSet resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                accountTypes.add(new AccountType(
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountTypes;
    }

    @Override
    public void update(AccountType entity, Integer id) {
        String updateLoginQuery = "UPDATE AccountType SET description = ? WHERE id = ? ";
        try {
            statement = getPrepareStatement(updateLoginQuery);
            statement.setString(1, entity.getDescription());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccountType getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM AccountType WHERE id = ?";
        AccountType accountType = new AccountType();
        try {
            statement = getPrepareStatement(getEntityByIdQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                accountType.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountType;
    }

    @Override
    public boolean delete(Integer id) {
        String deleteTypeQuery = "DELETE FROM AccountType WHERE id = ?";
        try {
            statement = getPrepareStatement(deleteTypeQuery);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(AccountType entity) {
        String insertQuery = "INSERT INTO AccountType (description) VALUES (?)";
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
