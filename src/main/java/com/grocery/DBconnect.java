package com.grocery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
    private static DBconnect single_instance = null;
    private Connection connection = null;

    private DBconnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            System.out.println("DB Connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e);
        }
    }

    public static DBconnect getInstance() {
        if (single_instance == null) {
            single_instance = new DBconnect();
        }
        return single_instance;
    }
}
