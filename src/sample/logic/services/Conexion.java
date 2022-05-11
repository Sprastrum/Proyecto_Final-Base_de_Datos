package sample.logic.services;

import java.sql.*;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/world";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL_USERNAME = "root";
    private static final String URL_PASSWORD = "1234";
    private static Connection connection;

    public void connect() {
        connection = null;
        try{
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection(URL, URL_USERNAME, URL_PASSWORD);
            System.out.println("Success");
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println(e);
        }
    }

    public static void closeConnection(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void closeConnection(PreparedStatement resultSet) {
        try {
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void closeConnection(java.sql.Connection resultSet) {
        try {
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}