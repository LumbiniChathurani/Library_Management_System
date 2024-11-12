package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {
    private static Connection connection;
    final static String DB_URL = "jdbc:mysql://localhost:3306/library_system";
    final static String DB_USER = "root";
    final static String DB_PASSWORD = "mysql";
    public static Connection getConnection() throws SQLException {
        if(connection==null){
            System.out.println("Getting DB connection");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("DB connection success");
        }
        return connection;
    }
}
