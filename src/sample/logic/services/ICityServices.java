package sample.logic.services;

import sample.logic.entities.City;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ICityServices {
    City findIndex(String id) throws PersonaException;
    List<City> getAll();
    City insert(City persona) throws IOException, PersonaException;
    void modify(City newPersona,City personaToReplace);
    boolean delete(City persona) throws PersonaException;
    void export(Character characterSeparate) throws FileNotFoundException;
}
