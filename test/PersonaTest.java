import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.logic.entities.Persona;
import sample.logic.services.PersonaException;

public class PersonaTest {
    public String name ="Diana";
    public String lastName = "Sanchez";

    @Test
    public void ShouldAddCorrectlyAPerson(){
        Assertions.assertDoesNotThrow(()->
                new Persona(name,lastName,ConstantsForTests.VALID_AGE,ConstantsForTests.VALID_SEX,
                ConstantsForTests.VALID_DEPARTMENT,ConstantsForTests.VALID_STATE,"Abuso Policial",
                ConstantsForTests.VALID_ID));
    }

    @Test
    public void ShouldThrowUnderAgeException(){
        PersonaException result = Assertions.assertThrows(PersonaException.class ,() ->
                new Persona(name,lastName,Integer.toString(Persona.MIN_AGE-1),ConstantsForTests.VALID_SEX,
                        ConstantsForTests.VALID_DEPARTMENT,ConstantsForTests.VALID_STATE,"Abuso Policial",
                        ConstantsForTests.VALID_ID));
        Assertions.assertEquals(result.getMessage(),PersonaException.UNDER_AGE);
    }

    @Test
    public void ShouldThrowUpperAgeException(){
        PersonaException result = Assertions.assertThrows(PersonaException.class ,() ->
                new Persona(name,lastName,Integer.toString(Persona.MAX_AGE+1),ConstantsForTests.VALID_SEX,
                        ConstantsForTests.VALID_DEPARTMENT,ConstantsForTests.VALID_STATE,"Abuso Policial",
                        ConstantsForTests.VALID_ID));
        Assertions.assertEquals(result.getMessage(),PersonaException.UPPER_AGE);
    }

    @Test
    public void ShouldThrowUnderIdException(){
        PersonaException result = Assertions.assertThrows(PersonaException.class ,() ->
                new Persona(name,lastName,ConstantsForTests.VALID_AGE,ConstantsForTests.VALID_SEX,
                        ConstantsForTests.VALID_DEPARTMENT,ConstantsForTests.VALID_STATE,"Abuso Policial",
                        ConstantsForTests.INVALID_UNDER_ID));
        Assertions.assertEquals(result.getMessage(),PersonaException.UNDER_ID);
    }

    @Test
    public void ShouldThrowUpperIdException(){
        PersonaException result = Assertions.assertThrows(PersonaException.class ,() ->
                new Persona(name,lastName,ConstantsForTests.VALID_AGE,ConstantsForTests.VALID_SEX,
                        ConstantsForTests.VALID_DEPARTMENT,ConstantsForTests.VALID_STATE,"Abuso Policial",
                        ConstantsForTests.INVALID_UPPER_ID));
        Assertions.assertEquals(result.getMessage(),PersonaException.UPPER_ID);
    }
}
