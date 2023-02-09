package solvd.util;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

@Getter
@Setter
public class ConnectionUtil {
    private String user;
    private String password;
    private String url;
    private final String path = "src/main/resources/connection.properties";

    public ConnectionUtil() {
        getProperties();
    }

    private void getProperties() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = prop.getProperty("db.url");
        user = prop.getProperty("db.user");
        password = prop.getProperty("db.password");
    }
}
