package sample.logic.entities;

import sample.logic.services.PersonaException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a public employee affected by the national stoppage
 */
public class PublicEmployee extends Persona{
    private String position;

    /**
     * Initializes a person
     * @param reason why it was a victim
     * @param position where it works.
     * @throws PersonaException if there is a wrong parameter
     */
    public PublicEmployee(String name, String lastName, String age, String sex, String department, String condition, String reason, String id, String position) throws PersonaException {
        super(name, lastName, age, sex, department, condition, reason, id);
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    /**
     * String list of the attributes of the class
     * @return String list
     */
    @Override
    public List<String> toStringList() {
        List<String> result = new ArrayList<>();

        result.add(super.name);
        result.add(this.lastName);
        result.add(String.valueOf(this.age));
        result.add(this.sex);
        result.add(this.department);
        result.add(this.condition);
        result.add(this.reason);
        result.add(this.id);
        result.add(position);

        return result;
    }
    @Override
    /**
     * Converts the attributes of the class into a string
     * @return string
     */
    public String toString() {
        return super.toString() +
                "Posición: " + position;
    }
}
