package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {

    final static String DB_URL = "jdbc:mysql://localhost:3306/library_system";
    final static String DB_USER = "root";
    final static String DB_PASSWORD = "mysql";
    public static Connection getConnection() throws SQLException {

            System.out.println("Getting DB connection");
            var connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("DB connection success");

        return connection;
    }
}
