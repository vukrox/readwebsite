package src.main.java.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaMySQLTest {
    String url = "jdbc:mysql://localhost:3306/db_name";
    String username = "root";
    String password = "password";

    Connection connection;

    {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the DB");
        } catch (SQLException throwables) {
            System.out.println("Oops, can't connect");
            throwables.printStackTrace();
        }
    }

}
