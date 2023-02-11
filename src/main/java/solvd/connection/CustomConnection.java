package solvd.connection;

import solvd.exception.CustomException;
import solvd.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomConnection {
    private static BasicConnectionPool connectionPool;
    private static CustomConnection instance = null;

    private CustomConnection() {

    }

    public static CustomConnection getInstance() {
        if (instance==null)
            instance = new CustomConnection();
        return instance;
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
}
