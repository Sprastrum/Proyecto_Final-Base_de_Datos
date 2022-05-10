package sample.logic.services;

import sample.logic.entities.Persona;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IPersonaServices {
    Persona findIndex(String id) throws PersonaException;
    List<Persona> getAll();
    Persona insert(Persona persona) throws IOException, PersonaException;
    void modify(Persona newPersona,Persona personaToReplace);
    boolean delete(Persona persona) throws PersonaException;
    void export(Character characterSeparate) throws FileNotFoundException;
}
