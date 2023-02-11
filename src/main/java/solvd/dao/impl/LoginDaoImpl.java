package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.LoginDao;
import solvd.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static solvd.util.QueryCollection.*;

public class LoginDaoImpl implements LoginDao {
    Connection connection = CustomConnection.getInstance().getConnection();
    PreparedStatement statement;

    @Override
    public List<Login> getAll() {
        List<Login> logins = new ArrayList<>();
        try {
            statement = connection.prepareStatement(selectAllLoginsQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                logins.add(getLoginFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logins;
    }

    @Override
    public void update(Login login, Integer id) {
        try {
            statement = connection.prepareStatement(updateLoginQuery);
            statement.setString(1, login.getName());
            statement.setString(2, login.getPassword());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Login getEntityById(Integer id) {
        Login login = new Login();
        try {
            statement = connection.prepareStatement(getLoginByIdQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                login.setName(rs.getString("name"));
                login.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            statement = connection.prepareStatement(deleteLoginQuery);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Login login) {
        try {
            statement = connection.prepareStatement(insertLoginQuery);
            setStatement(statement, login);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Login getLoginFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        int customer_id = resultSet.getInt("customer_id");
        return new Login(name, password, customer_id);
    }

    private void setStatement(PreparedStatement statement, Login login) {
        try {
            statement.setString(1, login.getName());
            statement.setString(2, login.getPassword());
            statement.setInt(3, login.getCustomer_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
