package sample.logic.persistence.implementation;

import sample.gui.SetUp;
import sample.logic.ValidPublicEmployees;
import sample.logic.entities.Persona;
import sample.logic.entities.PublicEmployee;
import sample.logic.persistence.PersistenceException;
import sample.logic.services.IPersonaServices;
import sample.logic.services.PersonaException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Class that allows to verify and save a people csv file into the dataBase
 */
public class Import {
    SetUp setUp;
    IPersonaServices personaServices;

    /**
     * Creates an instance of import.
     * @param personaServices of the main scene.
     */
    public Import(IPersonaServices personaServices) {
        setUp = new SetUp();
        this.personaServices = personaServices;
    }

    /**
     * method that verifies if the given csv file header corresponds to the header structure of the program.
     * @param file to verify
     * @throws PersistenceException if the header does not correspond.
     */
    public void verifyFileHeader(File file) throws IOException, PersistenceException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String header =br.readLine();
        ArrayList<String> readList = new ArrayList<>(Arrays.asList(header.split(",")));
        List<String> setUpList = setUp.stringList();

        for (int i = 0; i < setUp.stringList().size(); i++) {
            if (!(setUpList.contains(readList.get(i)))) {
                throw new PersistenceException(PersistenceException.WRONG_IMPORT_HEADER);
            }
        }
    }

    /**
     * Converts a csv valid file into people inside the database.
     * @param file to convert
     * @return the identifications of the people who were not added.
     * @throws PersonaException if there is something wrong with the person
     */
    public List<String>  fileToPersonas(File file) throws IOException, PersonaException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();

        List<String> repeated = new ArrayList<>();
        List<String> list;
        String temp;
        boolean isPublicEmployee = false;

        while ((temp = br.readLine()) != null){
            list = Arrays.asList(temp.split(","));

            for (ValidPublicEmployees v : ValidPublicEmployees.values()) {
                if (list.get(8).equals(v.toString())) {
                    isPublicEmployee = true;
                }
            }

            try {
                if (!isPublicEmployee) {
                    personaServices.insert(new Persona(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4),
                            list.get(5), list.get(6), list.get(7)));
                }
                else {
                    personaServices.insert(new PublicEmployee(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4),
                            list.get(5), list.get(6), list.get(7), list.get(8)));
                }
            } catch (PersonaException exception){
                repeated.add(list.get(7));
            }
            isPublicEmployee = false;
        }
        return repeated;
    }
}
