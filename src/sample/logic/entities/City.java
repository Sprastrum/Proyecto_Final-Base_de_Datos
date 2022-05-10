package sample.logic.entities;

public class City {
    protected int id;
    protected String countryCode;
    protected String district;
    protected String name;
    protected int population;

    public City(int id, String countryCode, String district, String name, int population) {
        this.id = id;
        this.countryCode = countryCode;
        this.district = district;
        this.name = name;
        this.population = population;
    }

    public City(String countryCode, String district, String name, int population) {
        this.countryCode = countryCode;
        this.district = district;
        this.name = name;
        this.population = population;
    }

    public City(int id) {
        this.id = id;
    }

    public City() {
    }

    public int getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
