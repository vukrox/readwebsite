package src.main.java.com.jdbc;

import java.sql.*;

public class Database extends Configues {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/websitesaver?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Na4uhudonossor";

    private static Connection dbConnection = null;

    public Database() {
        try {
            Class.forName(JDBC_DRIVER);
            dbConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Database Connection Initialized.");
    }

    public void closeConnection() {
        if (dbConnection == null) return;
        try {
            dbConnection.close();
            dbConnection = null;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean execute(String sql) throws SQLException {
        if (dbConnection == null)
            throw new SQLException("Connection null!");
        Statement statement = dbConnection.createStatement();
        boolean res = statement.execute(sql);
        statement.close();
        return res;
    }

    public int executeUpdate(String sql) throws SQLException {
        if (dbConnection == null)
            throw new SQLException("Connection null!");
        Statement statement = dbConnection.createStatement();
        int res = statement.executeUpdate(sql);
        statement.close();
        return res;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        if (dbConnection == null)
            throw new SQLException("Connection null!");
        Statement statement = dbConnection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        statement.close();
        return res;
    }
}

