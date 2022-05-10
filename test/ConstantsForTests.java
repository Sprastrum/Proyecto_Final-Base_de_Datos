import sample.logic.entities.Persona;

public class ConstantsForTests {
    //Valid
    public static final String VALID_SEX= "Male";
    public static final String VALID_DEPARTMENT= "Cundinamarca";
    public static final String VALID_AGE= Integer.toString(Persona.MIN_AGE+10);
    private static String number = "1";
    public static final String VALID_ID= number.repeat(Persona.MIN_ID_DIGITS);
    public static final String VALID_STATE= "Herido";
    public static final String VALID_POSITION= "Manifestante";

    //Invalid
    public static final String INVALID_AGE= Integer.toString(Persona.MIN_AGE-10);
    public static final String INVALID_UNDER_ID= number.repeat(Persona.MIN_ID_DIGITS-1);
    public static final String INVALID_UPPER_ID= number.repeat(Persona.MAX_ID_DIGITS+1);

}
