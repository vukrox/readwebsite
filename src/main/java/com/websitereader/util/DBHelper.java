package com.websitereader.util;

import java.sql.*;
import java.util.Properties;

public class DBHelper {
    private final String driver;
    private final String url;
    private final String login;
    private final String password;

    private static Connection dbConnection = null;

    public DBHelper(Properties properties) {
        this.driver = properties.getProperty("db.driver");
        this.url = properties.getProperty("db.url");
        this.login = properties.getProperty("db.login");
        this.password = properties.getProperty("db.password");
        init();
    }

    private void init() {
        try {
            Class.forName(driver);
            dbConnection = DriverManager.getConnection(url, login, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Database Connection Initialized.");
    }

    public void closeConnection() {
        if (dbConnection == null) {
            return;
        }
        try {
            dbConnection.close();
            dbConnection = null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean execute(String sql) throws SQLException {
        if (dbConnection == null) {
            throw new SQLException("Connection null!");
        }
        Statement statement = dbConnection.createStatement();
        boolean res = statement.execute(sql);
        statement.close();
        return res;
    }

    public int executeUpdate(String sql) throws SQLException {
        if (dbConnection == null) {
            throw new SQLException("Connection null!");
        }
        Statement statement = dbConnection.createStatement();
        int res = statement.executeUpdate(sql);
        statement.close();
        return res;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        if (dbConnection == null) {
            throw new SQLException("Connection null!");
        }
        Statement statement = dbConnection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        statement.close();
        return res;
    }
}
