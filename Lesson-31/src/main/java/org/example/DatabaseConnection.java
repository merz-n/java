package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class DatabaseConnection {
    private static String url = "jdbc:postgresql://localhost:5432/library";
    private static String user = "admin";
    private static String password = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

}
