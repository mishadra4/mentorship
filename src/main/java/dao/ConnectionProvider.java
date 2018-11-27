package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static final String URL = "jdbc:mysql://127.0.0.1/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String JDBC_DRIVER_URL = "com.mysql.cj.jdbc.Driver";

    public static Connection getLibraryDBConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER_URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
