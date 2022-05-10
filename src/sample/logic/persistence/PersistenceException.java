package sample.logic.persistence;

public class PersistenceException extends Exception{

    public static final String WRONG_IMPORT_HEADER = "The header of the given file is wrong";

    public PersistenceException(String message) {
        super(message);
    }
}
