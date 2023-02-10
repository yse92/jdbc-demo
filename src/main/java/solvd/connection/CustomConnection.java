package solvd.connection;

import solvd.connection.BasicConnectionPool;
import solvd.exception.CustomException;
import solvd.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomConnection {
    private Connection connection;
    private BasicConnectionPool connectionPool;

    public CustomConnection() {
        connection = getConnection();
    }

    public Connection getConnection() {
        try {
            ConnectionUtil c = new ConnectionUtil();
            connectionPool = BasicConnectionPool.create(c.getUrl(), c.getUser(), c.getPassword());
            return connectionPool.getConnection();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
