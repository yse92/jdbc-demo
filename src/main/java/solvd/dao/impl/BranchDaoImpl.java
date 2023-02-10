package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.BranchDao;
import solvd.dao.GenericDao;
import solvd.exception.CustomException;
import solvd.model.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static solvd.util.PermissionUtil.setForeignKeyChecks;

public class BranchDaoImpl extends CustomConnection implements BranchDao {
    PreparedStatement statement;

    @Override
    public List<Branch> getAll() {
        String selectAllBranchesQuery = "SELECT * FROM Branch";
        List<Branch> branches = new ArrayList<>();
        statement = getPrepareStatement(selectAllBranchesQuery);
        try {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                branches.add(new Branch(
                        resultSet.getString("adress"),
                        resultSet.getString("phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return branches;
    }

    @Override
    public void update(Branch entity, Integer id) {
        String updateBranchQuery = "UPDATE Branch SET adress = ? , phone = ? WHERE branchID = ?";
        try {
            statement = getPrepareStatement(updateBranchQuery);
            statement.setString(1, entity.getAdress());
            statement.setString(2, entity.getPhone());
            statement.setInt(3, id);
            setForeignKeyChecks(false, getConnection());
            statement.executeUpdate();
            setForeignKeyChecks(true, getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Branch getEntityById(Integer id) {
        String getEntityByIdQuery = "SELECT * FROM Branch WHERE branchID = ?";
        Branch branch = new Branch();
        try {
            statement = getPrepareStatement(getEntityByIdQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                branch.setAdress(rs.getString("adress"));
                branch.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return branch;
    }

    @Override
    public boolean delete(Integer id) {
        String deleteBranchQuery = "DELETE FROM Branch WHERE branchID = ?";
        try {
            statement = getPrepareStatement(deleteBranchQuery);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Branch entity) {
        String insertQuery = "INSERT INTO Branch (adress, phone) VALUES (?,?)";
        statement = getPrepareStatement(insertQuery);
        try {
            statement.setString(1, entity.getAdress());
            statement.setString(2, entity.getPhone());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
