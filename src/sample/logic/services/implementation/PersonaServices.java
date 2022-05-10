package sample.logic.services.implementation;

import javafx.collections.FXCollections;
import sample.logic.entities.Exportable;
import sample.logic.entities.Persona;
import sample.logic.persistence.IExport;
import sample.logic.persistence.IPersonaPersistence;
import sample.logic.persistence.implementation.Export;
import sample.logic.persistence.implementation.PersonaPersistence;
import sample.logic.services.IPersonaServices;
import sample.logic.services.PersonaException;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that manipulates all the people created.
 */
public class PersonaServices implements IPersonaServices {
    private IPersonaPersistence personasDataBase;
    private List<Persona>personas;
    private IExport export;

    /**
     * Creates an instance of PersonaServices. It links the persistence list with the local one.
     */
    public PersonaServices() {
        personas = FXCollections.observableArrayList();
        try {
            personasDataBase = new PersonaPersistence();
            personas.addAll(personasDataBase.read());
            this.export = new Export();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a person id, then it returns such person.
     * @param id for searching
     * @return required person
     * @throws PersonaException if the person does´nt exist
     */
    @Override
    public Persona findIndex(String id) throws PersonaException {
        Persona result = null;
        int index = 0;

        if(Integer.parseInt(id) >= 10000000) {
            for(index = 0; index < personas.size(); index++) {
                if(id.equals(personas.get(index).getId())) {
                    result = personas.get(index);
                    break;
                }
            }
            if(result == null) {
                throw new PersonaException(PersonaException.NULL_ID);
            }
        }
        else {
            throw new PersonaException(PersonaException.INVALID_CHARACTERS);
        }
        return result;

    }

    /**
     * @return the local personas list
     */
    @Override
    public List<Persona> getAll() {
        return personas;
    }

    /**
     * Introduces the given person to the local list and to the persistence.
     * @param persona to insert
     * @return persona added
     * @throws PersonaException if the person to add has something wrong
     */
    @Override
    public Persona insert(Persona persona) throws IOException, PersonaException {
        boolean create = true;

        for (Persona p: personas){
            if (p.getId().equals(persona.getId())){
                create = false;
                break;
            }
        }
        if (create) {
            personas.add(persona);
            personasDataBase.save(persona, true);
        }
        if (!create) {
            throw new PersonaException(PersonaException.EQUAL_ID);
        }
        return persona;
    }

    @Override
    public void modify(Persona newPersona, Persona personaToReplace ) {
        try {
            personasDataBase.replace(newPersona,personaToReplace);
            personas.set(personas.indexOf(personaToReplace), newPersona);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Deletes a given person from the local list and from the persistence.
     * @param persona to delete
     * @return if it was deleted
     * @throws PersonaException if there was something wrong with the person
     */
    @Override
    public boolean delete(Persona persona) throws PersonaException {
        if(!this.personas.contains(persona)) {
            throw new PersonaException(PersonaException.INVALID_PERSON);
        }
        try {
            personasDataBase.save(persona, false);
            this.personas.remove(persona);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Adds all the people to an exportable list and calls for its exportation.
     * @param characterSeparate of the extension
     */
    @Override
    public void export(Character characterSeparate) throws FileNotFoundException {
        List<Exportable> exportableList = new ArrayList<>();
        this.personas.stream().forEach(p -> exportableList.add(p));
        this.export.export(exportableList, characterSeparate);
    }
}
