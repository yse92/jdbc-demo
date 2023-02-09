package solvd.util;

import solvd.connection.CustomConnection;
import solvd.exception.CustomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static solvd.util.Converter.convertToBit;

public class PermissionUtil {
    public static void setForeignKeyChecks(boolean flag, Connection connection) {
        String q = "SET FOREIGN_KEY_CHECKS = ?";
        try (PreparedStatement statement = connection.prepareStatement(q)) {
            statement.setInt(1, convertToBit(flag));
            statement.execute();
        } catch (SQLException e) {
            throw new CustomException("Error ", e);
        }
    }
}
