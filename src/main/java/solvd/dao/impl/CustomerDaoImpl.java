package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.CustomerDao;
import solvd.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import static solvd.util.QueryCollection.*;

public class CustomerDaoImpl implements CustomerDao {
    Connection connection = CustomConnection.getInstance().getConnection();
    PreparedStatement statement;

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_CUSTOMERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                customers.add(getCustomerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void update(Customer customer, Integer id) {
        try {
            statement = connection.prepareStatement(UPDATE_CUSTOMER_QUERY);
            setStatement(statement, customer);
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getEntityById(Integer id) {
        Customer customer = new Customer();
        try {
            statement = connection.prepareStatement(GET_CUSTOMER_QUERY);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                customer.setFirstName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            statement = connection.prepareStatement(DELETE_CUSTOMER_QUERY);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Customer customer) {
        try {
            statement = connection.prepareStatement(INSERT_CUSTOMER_QUERY);
            setStatement(statement, customer);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Customer getCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String phone = resultSet.getString("phone");
        return new Customer(firstName, lastName, phone);
    }

    
    private void setStatement(PreparedStatement statement, Customer customer) {
        try {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
