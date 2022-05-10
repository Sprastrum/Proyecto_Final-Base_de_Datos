package sample.logic.services;

import com.mysql.cj.conf.DatabaseUrlContainer;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connection {
    private static final String URL = "jdbc:mysql://localhost:3306/world";
    private static final String URL_USERNAME = "root";
    private static final String URL_PASSWORD = "1234";
    private static Data dataSource;

    public static DataSource getDataSource() throws SQLException {
        if(dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.getConnection(URL_USERNAME, URL_PASSWORD);
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return (Connection) getDataSource().getConnection();
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