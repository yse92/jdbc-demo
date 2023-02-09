package solvd.dao;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;
import solvd.model.Customer;
import solvd.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements GenericDao<Employee, Integer> {

    @Override
    public List<Employee> getAll() {
        String selectAllCustomersQuery = "SELECT * FROM Employee";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllCustomersQuery);
             ResultSet resultSet = statement.executeQuery()) {
                List<Employee> employees = new ArrayList<>();
                while (resultSet.next()) {
                    employees.add(new Employee(
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("position"),
                            resultSet.getFloat("salary"),
                            resultSet.getInt("branch_id")
                    ));
                }
                return employees;
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public void update(Employee employee, Integer id) {
        String updateCustomerQuery = "UPDATE Employee SET firstName = ? , lastName = ? , position = ?, " +
                "salary = ? , branch_id = ? WHERE id = ? ";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateCustomerQuery)) {
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getPosition());
                statement.setFloat(4, employee.getSalary());
                statement.setInt(5, employee.getBranch_id());
                statement.setInt(6, id);
                statement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public Employee getEntityById(Integer id) {
        String getCustomerQuery = "SELECT * FROM Employee WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getCustomerQuery)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    Employee employee = new Employee();
                    while(rs.next()) {
                        employee.setFirstName(rs.getString("firstName"));
                        employee.setLastName(rs.getString("lastName"));
                        employee.setPosition(rs.getString("position"));
                        employee.setSalary(rs.getFloat("salary"));
                        employee.setBranch_id(rs.getInt("branch_id"));
                    }
                    return employee;
            }
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteEmployeeQuery = "DELETE FROM Employee WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteEmployeeQuery)) {
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean insert(Employee employee) {
        String insertQuery = "INSERT INTO Employee (firstName, lastName, position, salary, branch_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getPosition());
            statement.setFloat(4, employee.getSalary());
            statement.setInt(5, employee.getBranch_id());
            return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }
}
