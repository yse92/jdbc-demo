package solvd.dao;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;
import solvd.model.Customer;
import solvd.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomerDao implements GenericDao<Customer, Integer> {

    @Override
    public List<Customer> getAll() {
        String selectAllCustomersQuery = "SELECT * FROM CUSTOMER";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllCustomersQuery);
             ResultSet resultSet = statement.executeQuery()) {
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phone")
                ));
            }
            return customers;
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public void update(Customer customer, Integer id) {
        String updateCustomerQuery = "UPDATE Customer SET firstName = ? , lastName = ? , phone = ? WHERE id = ? ";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateCustomerQuery)) {
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setString(3, customer.getPhone());
                statement.setInt(4, id);
                statement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public Customer getEntityById(Integer id) {
        String getCustomerQuery = "SELECT * FROM Customer WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getCustomerQuery)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    Customer customer = new Customer();
                    while(rs.next()) {
                        customer.setFirstName(rs.getString("firstName"));
                        customer.setLastName(rs.getString("lastName"));
                        customer.setPhone(rs.getString("phone"));
                    }
                    return customer;
                }
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteCustomerQuery = "DELETE FROM Customer WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteCustomerQuery)) {
                statement.setInt(1, id);
                return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean insert(Customer customer) {
        String insertQuery = "INSERT INTO Customer (firstName, lastName, phone) VALUES (?, ?, ?)";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setString(3, customer.getPhone());
                return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }
}
