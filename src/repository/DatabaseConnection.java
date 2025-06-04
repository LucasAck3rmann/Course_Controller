package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Connection
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/course_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "toor";

    // Open Connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}