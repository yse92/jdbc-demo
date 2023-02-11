package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.AccountTypeDao;
import solvd.model.AccountType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static solvd.util.QueryCollection.*;

public class AccountTypeDaoImpl implements AccountTypeDao {
    Connection connection = CustomConnection.getInstance().getConnection();
    PreparedStatement statement;

    @Override
    public List<AccountType> getAll() {
        List<AccountType> accountTypes = new ArrayList<>();
        try {
            statement = connection.prepareStatement(selectAllAccountTypesQuery);
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
        try {
            statement = connection.prepareStatement(updateAccountTypeQuery);
            statement.setString(1, entity.getDescription());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccountType getEntityById(Integer id) {
        AccountType accountType = new AccountType();
        try {
            statement = connection.prepareStatement(getAccountTypeByIdQuery);
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
        try {
            statement = connection.prepareStatement(deleteAccountTypeQuery);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(AccountType entity) {
        try {
            statement = connection.prepareStatement(insertAccountTypeQuery);
            statement.setString(1, entity.getDescription());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
