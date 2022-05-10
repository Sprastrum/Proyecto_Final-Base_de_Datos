package sample.logic.entities;

/**
 * Represents a report of a database
 */
public class Report {

    private String information, description;
    private double count;

    /**
     * generates an instance of report
     * @param information interest data
     * @param count of cases that satisfy the interest data
     * @param description of the report
     */
    public Report(String information, double count, String description) {
        this.information = information;
        this.count = count;
        this.description = description;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * Method that increments 1 the count
     */
    public void incrementCount() {
        count++;
    }
}
