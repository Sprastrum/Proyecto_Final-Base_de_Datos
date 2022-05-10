package sample.logic.persistence;

import sample.logic.entities.Persona;

import java.io.IOException;
import java.util.List;

public interface IPersonaPersistence {
    void save(Persona person, boolean option) throws IOException;

    List<Persona> read() throws IOException, ClassNotFoundException;

    void replace(Persona newPersona,Persona personaToReplace) throws IOException;

    void write() throws IOException;
}
