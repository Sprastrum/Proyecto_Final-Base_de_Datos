package sample.logic.entities;

import javafx.scene.Node;
import sample.gui.SetUp;
import sample.logic.services.PersonaException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a person affected by the national stoppage
 */
public class Persona extends Exportable implements Serializable {
    protected String name;
    protected String lastName;
    protected int age;
    private final String postion = "Civil";
    protected String sex;
    protected String department;
    protected String condition;
    protected String reason;
    protected String id;
    public static final int MAX_ID_DIGITS = 10;
    public static final int MIN_ID_DIGITS = 8;
    public static final int MAX_AGE = 150;
    public static final int MIN_AGE = 1;

    /**
     * Initializes a person
     * @param reason why it was a victim
     * @throws PersonaException if there is a wrong parameter
     */
    public Persona(String name, String lastName, String age, String sex, String department, String condition, String reason, String id) throws PersonaException {
        this.name = name;
        this.lastName = lastName;
        setAge(age);
        this.sex = sex;
        this.department = department;
        this.condition = condition;
        this.reason = reason;
        setId(id);
    }

    public String getName() {
        return this.name;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getCondition() {
        return this.condition;
    }

    public String getPosition() { return postion;}

    public String getSex() { return this.sex;}

    public int getAge() { return age; }

    public String getDepartment() { return department; }

    public String getReason() { return reason; }

    public String getId() {
        return this.id;
    }

    /**
     * Method for setting the age that takes into account that the given one have a value between the min and max age.
     * @param inputAge for setting
     * @throws PersonaException if the age is not valid
     */
    private void setAge(String inputAge) throws PersonaException {
        try {
            int result = Integer.parseInt(inputAge);
            if (result> MAX_AGE) throw new PersonaException(PersonaException.UPPER_AGE);
            if (result< MIN_AGE) throw new PersonaException(PersonaException.UNDER_AGE);
            this.age = result;
        } catch (NumberFormatException e) {
            throw new PersonaException(PersonaException.INVALID_CHARACTERS);
        }

    }

    /**
     * Method for setting the id that takes into account that the given value is between the established
     * max and min values.
     * @param inputId to setup
     * @throws PersonaException if the id isnt valid
     */
    private void setId(String inputId) throws PersonaException {
        try {
            Long.parseLong(inputId);
            if(inputId.length()< MIN_ID_DIGITS) throw new PersonaException(PersonaException.UNDER_ID);
            if(inputId.length()> MAX_ID_DIGITS) throw new PersonaException(PersonaException.UPPER_ID);
            this.id = inputId;
        } catch (NumberFormatException e) {
            throw new PersonaException(PersonaException.INVALID_CHARACTERS);
        }
    }

    /**
     * Converts the attributes of the class into a string
     * @return string
     */
    @Override
    public String toString() {
        return String.format("Nombre Completo: " + name + " " + lastName + "\n"
                + "Edad: " + age + "\n"
                + "Sexo: " + sex + "\n"
                + "Cédula: " + id + "\n"
                + "Departamento: " + department + "\n"
                + "Condición: " + condition + "\n"
                + "Razón: " + reason + "\n");
    }

    /**
     * String list of the attributes of the class
     * @return String list
     */
    @Override
    public List<String> toStringList() {
        List<String> result = new ArrayList<>();

        result.add(this.name);
        result.add(this.lastName);
        result.add(String.valueOf(this.age));
        result.add(this.sex);
        result.add(this.department);
        result.add(this.condition);
        result.add(this.reason);
        result.add(this.id);
        result.add(this.postion);

        return result;
    }

    /**
     * Sets the header for all the attributes of the class
     * @param separateCharacter for splitting the string
     * @return header string
     */
    @Override
    public String getHeader(Character separateCharacter) {
        return SetUp.NAME + separateCharacter + SetUp.LAST_NAME + separateCharacter + SetUp.AGE + separateCharacter + SetUp.AGE +
                separateCharacter + SetUp.DEPARTMENT + separateCharacter + SetUp.CONDITION + separateCharacter + SetUp.REASON +
                separateCharacter + SetUp.ID + separateCharacter + SetUp.POSITION;
    }
}
