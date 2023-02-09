package solvd.dao;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;
import solvd.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDao implements GenericDao<Login, Integer>{

    @Override
    public List<Login> getAll() {
        String selectAllLoginsQuery = "SELECT * FROM Login";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllLoginsQuery);
             ResultSet resultSet = statement.executeQuery()) {
                List<Login> logins = new ArrayList<>();
                while (resultSet.next()) {
                    logins.add(new Login(
                            resultSet.getString("name"),
                            resultSet.getString("password"),
                            resultSet.getInt("customer_id")
                    ));
                }
                return logins;
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public void update(Login login, Integer id) {
        String updateLoginQuery = "UPDATE Login SET name = ? , password = ? WHERE id = ? ";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateLoginQuery)) {
                statement.setString(1, login.getName());
                statement.setString(2, login.getPassword());
                statement.setInt(3, id);
                statement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public Login getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM Login WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getEntityByIdQuery)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    Login login = new Login();
                    while(rs.next()) {
                        login.setName(rs.getString("name"));
                        login.setPassword(rs.getString("password"));
                    }
                    return login;
                }
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteLoginQuery = "DELETE FROM Login WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteLoginQuery)) {
                statement.setInt(1, id);
                return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean insert(Login login) {
        String insertQuery = "INSERT INTO Login (name, password, customer_id) VALUES (?,?,?)";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, login.getName());
                statement.setString(2, login.getPassword());
                statement.setInt(3, login.getCustomer_id());
                return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

}
