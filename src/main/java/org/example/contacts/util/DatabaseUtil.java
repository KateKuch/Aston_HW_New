package org.example.contacts.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DatabaseUtil {

    private static final String PROPERTIES_FILE = "/db.properties";
    private static final Properties properties = new Properties();

    static {
        try{
            properties.load(DatabaseUtil.class.getResourceAsStream(PROPERTIES_FILE));
            Class.forName("org.postgresql.Driver");
        }catch (Exception e){
            throw new RuntimeException("Failed to load database properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );
    }
}
