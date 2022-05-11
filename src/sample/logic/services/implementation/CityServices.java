package sample.logic.services.implementation;
import sample.logic.entities.City;
import sample.logic.services.Conexion;
import sample.logic.services.ICityServices;
import sample.logic.services.PersonaException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;

public class CityServices implements ICityServices {

    public CityServices() {

    }

    @Override
    public City findIndex(String id) throws PersonaException {


        return null;
    }

    @Override
    public List<City> getAll() {
        return null;
    }

    @Override
    public City insert(City persona) throws IOException, PersonaException {
        return null;
    }

    @Override
    public void modify(City newPersona, City personaToReplace) {

    }

    @Override
    public boolean delete(City persona) throws PersonaException {
        return false;
    }

    @Override
    public void export(Character characterSeparate) throws FileNotFoundException {

    }
}
