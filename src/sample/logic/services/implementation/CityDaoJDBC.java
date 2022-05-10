package sample.logic.services.implementation;
import sample.logic.entities.City;
import sample.logic.services.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CityDaoJDBC {

    private static final String SQL_SELECT = "SELECT id, countrycode, district, name, population FROM city";
    private Connection connection;
    PreparedStatement preparedStatement = null;

    public List<City> list() {

        try {
            connection = Connection.getConnection();
            preparedStatement = connection.
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
