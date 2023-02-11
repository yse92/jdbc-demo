package solvd.dao.impl;

import solvd.connection.CustomConnection;
import solvd.dao.BranchDao;
import solvd.model.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static solvd.util.PermissionUtil.setForeignKeyChecks;
import static solvd.util.QueryCollection.*;

public class BranchDaoImpl implements BranchDao {
    Connection connection = CustomConnection.getInstance().getConnection();
    PreparedStatement statement;

    @Override
    public List<Branch> getAll() {
        List<Branch> branches = new ArrayList<>();
        try {
            statement = connection.prepareStatement(selectAllBranchesQuery);
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
        try {
            statement = connection.prepareStatement(updateBranchQuery);
            statement.setString(1, entity.getAdress());
            statement.setString(2, entity.getPhone());
            statement.setInt(3, id);
            setForeignKeyChecks(false, connection);
            statement.executeUpdate();
            setForeignKeyChecks(true, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Branch getEntityById(Integer id) {
        Branch branch = new Branch();
        try {
            statement = connection.prepareStatement(getBranchByIdQuery);
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
        try {
            statement = connection.prepareStatement(deleteBranchQuery);
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Branch entity) {
        try {
            statement = connection.prepareStatement(insertBranchQuery);
            statement.setString(1, entity.getAdress());
            statement.setString(2, entity.getPhone());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
