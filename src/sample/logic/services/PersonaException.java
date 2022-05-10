package sample.logic.services;

public class PersonaException extends Exception{
    public static final String UNDER_AGE = "La edad introducida es menor a 0 años";
    public static final String UPPER_AGE = "La edad introducida es mayor a 150 años";
    public static final String UPPER_ID = "El Id supera los 10 digitos";
    public static final String UNDER_ID = "El Id tiene menos de 8 digitos";
    public static final String INVALID_CHARACTERS = "El parametro introducido es invalido";
    public static final String EQUAL_ID = "EL id ya existe en el sistema";
    public static final String NULL_ID = "El id no existe en el sistema";
    public static final String INVALID_PERSON = "La persona no existe en el sistema";




    public PersonaException(String message) {
        super(message);
    }
}
