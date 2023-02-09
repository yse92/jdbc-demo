package solvd.dao;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;
import solvd.model.Account;
import solvd.model.Loan;
import solvd.util.PermissionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static solvd.util.PermissionUtil.setForeignKeyChecks;

public class LoanDao implements GenericDao<Loan, Integer>{
    @Override
    public List<Loan> getAll() {
        String selectAllLoansQuery = "SELECT * FROM Loan";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllLoansQuery);
             ResultSet resultSet = statement.executeQuery()) {
                List<Loan> loans = new ArrayList<>();
                while(resultSet.next()) {
                    loans.add(new Loan(
                        resultSet.getDouble("amount"),
                        resultSet.getString("status"),
                        resultSet.getInt("loanType_id"),
                        resultSet.getInt("account_id"),
                        resultSet.getInt("employee_id")
                    ));
                }
                return loans;
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public void update(Loan entity, Integer id) {
        String updateLoanQuery = "UPDATE Loan SET amount = ? , status = ?, loanType_id = ?," +
                " account_id = ?, employee_id = ? WHERE id = ? ";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateLoanQuery)) {
                statement.setDouble(1, entity.getAmount());
                statement.setString(2, entity.getStatus());
                statement.setInt(3, entity.getLoanType_id());
                statement.setInt(4, entity.getAccount_id());
                statement.setInt(5, entity.getEmployee_id());
                statement.setInt(6, id);
                setForeignKeyChecks(false, connection);
                statement.executeUpdate();
                setForeignKeyChecks(true, connection);
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public Loan getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM Loan WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getEntityByIdQuery)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    Loan loan = new Loan();
                    while(rs.next()) {
                        loan.setAmount(rs.getDouble("amount"));
                        loan.setStatus(rs.getString("status"));
                        loan.setLoanType_id(rs.getInt("loanType_id"));
                        loan.setAccount_id(rs.getInt("account_id"));
                        loan.setEmployee_id(rs.getInt("employee_id"));
                    }
                    return loan;
                }
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteLoanQuery = "DELETE FROM Loan WHERE id = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteLoanQuery)) {
                statement.setInt(1, id);
                PermissionUtil.setForeignKeyChecks(false, connection);
            return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean insert(Loan entity) {
        String insertQuery = "INSERT INTO Loan (amount, status, loanType_id, account_id, employee_id) VALUES (?,?,?,?,?)";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setDouble(1, entity.getAmount());
                statement.setString(2, entity.getStatus());
                statement.setInt(3, entity.getLoanType_id());
                statement.setInt(4, entity.getAccount_id());
                statement.setInt(5, entity.getEmployee_id());
                PermissionUtil.setForeignKeyChecks(false, connection);
            return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }
}
