package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.LoanDao;
import solvd.exception.CustomException;
import solvd.model.Loan;
import solvd.util.PermissionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static solvd.util.PermissionUtil.setForeignKeyChecks;

public class LoanDaoImpl extends CustomConnection implements LoanDao {
    PreparedStatement statement;

    @Override
    public List<Loan> getAll() {
        String selectAllLoansQuery = "SELECT * FROM Loan";
        List<Loan> loans = new ArrayList<>();
        try {
            statement = getPrepareStatement(selectAllLoansQuery);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                loans.add(new Loan(
                    resultSet.getDouble("amount"),
                    resultSet.getString("status"),
                    resultSet.getInt("loanType_id"),
                    resultSet.getInt("account_id"),
                    resultSet.getInt("employee_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public void update(Loan entity, Integer id) {
        String updateLoanQuery = "UPDATE Loan SET amount = ? , status = ?, loanType_id = ?," +
                " account_id = ?, employee_id = ? WHERE id = ? ";
        try {
            statement = getPrepareStatement(updateLoanQuery);
            statement.setDouble(1, entity.getAmount());
            statement.setString(2, entity.getStatus());
            statement.setInt(3, entity.getLoanType_id());
            statement.setInt(4, entity.getAccount_id());
            statement.setInt(5, entity.getEmployee_id());
            statement.setInt(6, id);
            setForeignKeyChecks(false, getConnection());
            statement.executeUpdate();
            setForeignKeyChecks(true, getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Loan getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM Loan WHERE id = ?";
        Loan loan = new Loan();
        try {
            statement = getPrepareStatement(getEntityByIdQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                loan.setAmount(rs.getDouble("amount"));
                loan.setStatus(rs.getString("status"));
                loan.setLoanType_id(rs.getInt("loanType_id"));
                loan.setAccount_id(rs.getInt("account_id"));
                loan.setEmployee_id(rs.getInt("employee_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loan;
    }

    @Override
    public boolean delete(Integer id) {
        String deleteLoanQuery = "DELETE FROM Loan WHERE id = ?";
        try {
            statement = getPrepareStatement(deleteLoanQuery);
            statement.setInt(1, id);
            PermissionUtil.setForeignKeyChecks(false, getConnection());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Loan entity) {
        String insertQuery = "INSERT INTO Loan (amount, status, loanType_id, account_id, employee_id) VALUES (?,?,?,?,?)";
        statement = getPrepareStatement(insertQuery);
        try {
            statement.setDouble(1, entity.getAmount());
            statement.setString(2, entity.getStatus());
            statement.setInt(3, entity.getLoanType_id());
            statement.setInt(4, entity.getAccount_id());
            statement.setInt(5, entity.getEmployee_id());
            PermissionUtil.setForeignKeyChecks(false, getConnection());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
