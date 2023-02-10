package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.CustomerDao;
import solvd.model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomerDaoImpl extends CustomConnection implements CustomerDao {
    PreparedStatement statement;

    @Override
    public List<Customer> getAll() {
        String selectAllCustomersQuery = "SELECT * FROM CUSTOMER";
        List<Customer> customers = new ArrayList<>();
        try {
            statement = getPrepareStatement(selectAllCustomersQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customers.add(getCustomerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void update(Customer customer, Integer id) {
        String updateCustomerQuery = "UPDATE Customer SET firstName = ? , lastName = ? , phone = ? WHERE id = ? ";
        try {
            statement = getPrepareStatement(updateCustomerQuery);
            setStatement(statement, customer);
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getEntityById(Integer id) {
        String getCustomerQuery = "SELECT * FROM Customer WHERE id = ?";
        Customer customer = new Customer();
        try {
            statement = getPrepareStatement(getCustomerQuery);
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
        String deleteCustomerQuery = "DELETE FROM Customer WHERE id = ?";
        try {
            statement = getPrepareStatement(deleteCustomerQuery);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Customer customer) {
        String insertQuery = "INSERT INTO Customer (firstName, lastName, phone) VALUES (?, ?, ?)";
        try {
            statement = getPrepareStatement(insertQuery);
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
