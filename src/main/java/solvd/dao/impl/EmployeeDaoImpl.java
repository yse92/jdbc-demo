package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.EmployeeDao;
import solvd.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static solvd.util.QueryCollection.*;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection connection = CustomConnection.getInstance().getConnection();
    PreparedStatement statement;

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_EMPLOYEES_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void update(Employee employee, Integer id) {
        try {
            statement = connection.prepareStatement(UPDATE_EMPLOYEE_QUERY);
            setStatement(statement, employee);
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEntityById(Integer id) {
        Employee employee = new Employee();
        try {
            statement = connection.prepareStatement(GET_EMPLOYEE_QUERY);
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
        try {
            statement = connection.prepareStatement(DELETE_EMPLOYEE_QUERY);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Employee employee) {
        try {
            statement = connection.prepareStatement(INSERT_EMPLOYEE_QUERY);
            setStatement(statement, employee);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String position = resultSet.getString("position");
                float salary = resultSet.getFloat("salary");
                int branch_id = resultSet.getInt("branch_id");
        return new Employee(firstName, lastName, position, salary, branch_id);
    }

    private void setStatement(PreparedStatement statement, Employee employee) {
        try {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getPosition());
            statement.setFloat(4, employee.getSalary());
            statement.setInt(5, employee.getBranch_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
