package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_URL = "jdbc:mysql://localhost:3306/MySQL";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    public static Connection getConnection(){


        Connection connection = null;

        try {
//            Class.forName(DB_DRIVER);
//            Driver driver = new FabricMySQLDriver();
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("est kontact!!!");
        } catch (Exception e) {
            System.out.println("net kontakta(");
            throw new RuntimeException(e);

        }
        return connection;
    }
}
