package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.EmployeeDao;
import solvd.exception.CustomException;
import solvd.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl extends CustomConnection implements EmployeeDao {
    PreparedStatement statement;

    @Override
    public List<Employee> getAll() {
        String selectAllCustomersQuery = "SELECT * FROM Employee";
        List<Employee> employees = new ArrayList<>();
        try {
            statement = getPrepareStatement(selectAllCustomersQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("position"),
                    resultSet.getFloat("salary"),
                    resultSet.getInt("branch_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void update(Employee employee, Integer id) {
        String updateCustomerQuery = "UPDATE Employee SET firstName = ? , lastName = ? , position = ?, " +
                "salary = ? , branch_id = ? WHERE id = ? ";
        try {
            statement = getPrepareStatement(updateCustomerQuery);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getPosition());
            statement.setFloat(4, employee.getSalary());
            statement.setInt(5, employee.getBranch_id());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEntityById(Integer id) {
        String getCustomerQuery = "SELECT * FROM Employee WHERE id = ?";
        Employee employee = new Employee();
        try {
            statement = getPrepareStatement(getCustomerQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                employee.setFirstName(rs.getString("firstName"));
                employee.setLastName(rs.getString("lastName"));
                employee.setPosition(rs.getString("position"));
                employee.setSalary(rs.getFloat("salary"));
                employee.setBranch_id(rs.getInt("branch_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean delete(Integer id) {
        String deleteEmployeeQuery = "DELETE FROM Employee WHERE id = ?";
        try {
            statement = getPrepareStatement(deleteEmployeeQuery);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Employee employee) {
        String insertQuery = "INSERT INTO Employee (firstName, lastName, position, salary, branch_id) VALUES (?, ?, ?, ?, ?)";
        statement = getPrepareStatement(insertQuery);
        try {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getPosition());
            statement.setFloat(4, employee.getSalary());
            statement.setInt(5, employee.getBranch_id());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
