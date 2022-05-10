import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.logic.entities.Persona;
import sample.logic.services.IPersonaServices;
import sample.logic.services.PersonaException;
import sample.logic.services.implementation.PersonaServices;

import java.io.IOException;

public class PersonaServicesTest {

    private final String name = "Santiago";
    private final String lastName = "Santos";

    private final IPersonaServices personaServices = new PersonaServices();

    @Test
    public void ShouldGetListOfPersons() throws PersonaException, IOException {
        Persona p1 = new Persona(name, lastName, ConstantsForTests.VALID_AGE, ConstantsForTests.VALID_SEX,
                ConstantsForTests.VALID_DEPARTMENT, ConstantsForTests.VALID_STATE, "Abuso Policial",
                ConstantsForTests.VALID_ID);
        personaServices.insert(p1);
        Assertions.assertTrue(personaServices.getAll().size() > 0);

        Persona p2 = new Persona(name, lastName, ConstantsForTests.VALID_AGE, ConstantsForTests.VALID_SEX,
                ConstantsForTests.VALID_DEPARTMENT, ConstantsForTests.VALID_STATE, "Abuso Policial",
                ConstantsForTests.VALID_ID+1);
        personaServices.insert(p2);
        Assertions.assertTrue(personaServices.getAll().size() > 1);

        personaServices.delete(p1);
        personaServices.delete(p2);
    }

    @Test
    public void ShouldInsertAPerson() throws PersonaException, IOException {
        Persona p = new Persona(name, lastName, ConstantsForTests.VALID_AGE, ConstantsForTests.VALID_SEX,
                ConstantsForTests.VALID_DEPARTMENT, ConstantsForTests.VALID_STATE, "Abuso Policial",
                ConstantsForTests.VALID_ID);

        Assertions.assertNotNull(p);
        Assertions.assertNotNull(personaServices);

        Assertions.assertNotNull(personaServices.insert(p));
        personaServices.delete(p);
    }

    @Test
    public void ShouldDeleteAPerson() throws PersonaException, IOException {
        Persona p = new Persona(name, lastName, ConstantsForTests.VALID_AGE, ConstantsForTests.VALID_SEX,
                ConstantsForTests.VALID_DEPARTMENT, ConstantsForTests.VALID_STATE, "Abuso Policial",
                ConstantsForTests.VALID_ID);

        personaServices.insert(p);
        Assertions.assertNotNull(personaServices.getAll());

        Assertions.assertTrue(personaServices.delete(p));
    }

    @Test
    public void ShouldModifyAPerson() throws PersonaException, IOException {
        Persona p = new Persona(name, lastName, ConstantsForTests.VALID_AGE, ConstantsForTests.VALID_SEX,
                ConstantsForTests.VALID_DEPARTMENT, ConstantsForTests.VALID_STATE, "Abuso Policial",
                ConstantsForTests.VALID_ID);
        Assertions.assertEquals(p, personaServices.insert(p));

        personaServices.modify(new Persona("Paco", "Güiza", ConstantsForTests.VALID_AGE, ConstantsForTests.VALID_SEX,
                ConstantsForTests.VALID_DEPARTMENT, ConstantsForTests.VALID_STATE, "Abuso",
                ConstantsForTests.VALID_ID), p);

        Assertions.assertNotEquals(p, personaServices.findIndex(ConstantsForTests.VALID_ID));

        personaServices.delete(personaServices.findIndex(ConstantsForTests.VALID_ID));
    }

    @Test
    public void ShouldFindAPerson() throws PersonaException, IOException {
        Persona p = new Persona(name, lastName, ConstantsForTests.VALID_AGE, ConstantsForTests.VALID_SEX,
                ConstantsForTests.VALID_DEPARTMENT, ConstantsForTests.VALID_STATE, "Abuso Policial",
                ConstantsForTests.VALID_ID);
        personaServices.insert(p);

        Assertions.assertEquals(p, personaServices.findIndex(ConstantsForTests.VALID_ID));
        personaServices.delete(p);
    }

    @Test
    public void ShouldThrowNullIdException() {
        PersonaException result = Assertions.assertThrows(PersonaException.class, () -> personaServices.findIndex("10000000"));
        Assertions.assertEquals(result.getMessage(), PersonaException.NULL_ID);
    }

    @Test
    public void ShouldThrowInvalidPersonException() {
        PersonaException result = Assertions.assertThrows(PersonaException.class, () -> personaServices.delete(new Persona(
                name, lastName, ConstantsForTests.VALID_AGE, ConstantsForTests.VALID_SEX,
                ConstantsForTests.VALID_DEPARTMENT, ConstantsForTests.VALID_STATE, "Abuso Policial",
                ConstantsForTests.VALID_ID)));
        Assertions.assertEquals(result.getMessage(), PersonaException.INVALID_PERSON);
    }
}