package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private  static SessionFactory sessionFactory;


    public static Connection getConnection() {


        Connection connection = null;

        try {

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            if(!connection.isClosed()){
//                System.out.println("use u poryadke,shef!");}
        } catch (Exception e) {
            System.out.println("net kontakta(");
            e.printStackTrace();

        }
        return connection;
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties property = new Properties();
            property.setProperty("hibernate.connection.url", DB_URL);
            property.setProperty("hibernate.connection.username", DB_USERNAME);
            property.setProperty("hibernate.connection.password", DB_PASSWORD);
            property.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            property.setProperty("dialect", "org.hibernate.dialect.MySQL5Dialect");
            sessionFactory = new Configuration()
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                    .addProperties(property)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }


}
