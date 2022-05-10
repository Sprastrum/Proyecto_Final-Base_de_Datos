package sample.logic.persistence.implementation;

import sample.logic.entities.Persona;
import sample.logic.persistence.IPersonaPersistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that allows to persist information of a person.
 */
public class PersonaPersistence implements IPersonaPersistence, Serializable{
    private static final String PEOPLE_FILE_PATH = "people.database";
    private File file;
    private ObjectInputStream read;
    private ObjectOutputStream write;
    private List<Persona> data;

    /**
     * Constructor for creating an instance. It creates the database file if it doesn't exist.
     */
    public PersonaPersistence() throws IOException {
        file = new File(PEOPLE_FILE_PATH);
        if (file.createNewFile()) {
            System.out.println("The file "+ PEOPLE_FILE_PATH + " was created successfully");
        }
        data = new ArrayList<>();
    }

    /**
     * Method for saving a person. If the boolean value is true, it adds but if it´s false, it deletes.
     * @param persona to save
     * @param option to delete or add.
     */
    @Override
    public void save(Persona persona, boolean option) throws IOException {
        if (option) {
            data.add(persona);
        } else {
            data.remove(persona);
        }
        write();
    }

    /**
     * Method for reading the objects of the file where they are being saved.
     * @return list of people that was read.
     */
    @Override
    public List<Persona> read() throws IOException, ClassNotFoundException {
            if(file.length() != 0) {
                read = new ObjectInputStream(new FileInputStream(PEOPLE_FILE_PATH));
                data.addAll((ArrayList<Persona>)read.readObject());
                read.close();
        }
        return data;
    }

    /**
     * Method for replacing an specific person with a new one in the database file.
     * @param newPersona to add
     */
    @Override
    public void replace(Persona newPersona,Persona personaToReplace) throws IOException {
            data.set(data.indexOf(personaToReplace),newPersona);
            write();
    }

    /**
     * Method that overwrites the "data" list in the database file.
     */
    @Override
    public void write() throws IOException {
        write = new ObjectOutputStream(new FileOutputStream(PEOPLE_FILE_PATH));
        write.writeObject(data);
        write.close();
    }
}