package solvd.dao;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;
import solvd.model.AccountType;
import solvd.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountTypeDao implements GenericDao<AccountType, Integer> {
    @Override
    public List<AccountType> getAll() {
        String selectAllTypesQuery = "SELECT * FROM AccountType";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllTypesQuery);
             ResultSet resultSet = statement.executeQuery()) {
            List<AccountType> accountTypes = new ArrayList<>();
            while (resultSet.next()) {
                    accountTypes.add(new AccountType(
                    resultSet.getString("description")
                ));
            }
            return accountTypes;
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public void update(AccountType entity, Integer id) {
        String updateLoginQuery = "UPDATE AccountType SET description = ? WHERE id = ? ";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateLoginQuery)) {
                statement.setString(1, entity.getDescription());
                statement.setInt(2, id);
                statement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public AccountType getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM AccountType WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getEntityByIdQuery)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    AccountType accountType = new AccountType();
                    while(rs.next()) {
                        accountType.setDescription(rs.getString("description"));
                    }
                return accountType;
            }
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteTypeQuery = "DELETE FROM AccountType WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteTypeQuery)) {
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean insert(AccountType entity) {
        String insertQuery = "INSERT INTO AccountType (description) VALUES (?)";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, entity.getDescription());
            return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }
}
