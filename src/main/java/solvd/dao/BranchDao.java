package solvd.dao;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;
import solvd.model.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static solvd.util.PermissionUtil.setForeignKeyChecks;

public class BranchDao implements GenericDao<Branch, Integer>{

    @Override
    public List<Branch> getAll() {
        String selectAllBranchesQuery = "SELECT * FROM Branch";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllBranchesQuery);
             ResultSet resultSet = statement.executeQuery()) {
            List<Branch> branches = new ArrayList<>();
            while(resultSet.next()) {
                branches.add(new Branch(
                        resultSet.getString("adress"),
                        resultSet.getString("phone")
                ));
            }
            return branches;
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public void update(Branch entity, Integer id) {
        String updateBranchQuery = "UPDATE Branch SET adress = ? , phone = ? WHERE branchID = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateBranchQuery)) {
                statement.setString(1, entity.getAdress());
                statement.setString(2, entity.getPhone());
                statement.setInt(3, id);
                setForeignKeyChecks(false, connection);
                statement.executeUpdate();
                setForeignKeyChecks(true, connection);
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public Branch getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM Branch WHERE branchID = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getEntityByIdQuery)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    Branch branch = new Branch();
                    while(rs.next()) {
                        branch.setAdress(rs.getString("adress"));
                        branch.setPhone(rs.getString("phone"));
                    }
                    return branch;
                }
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteBranchQuery = "DELETE FROM Branch WHERE branchID = ?";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteBranchQuery)) {
                statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    @Override
    public boolean insert(Branch entity) {
        String insertQuery = "INSERT INTO Branch (adress, phone) VALUES (?,?)";
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, entity.getAdress());
                statement.setString(2, entity.getPhone());
                return statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }
}
