package maa.myfishing.service.models;

public class DestinationServiceModel {
    private String id;
    private String townName;
    private Integer population;
    private Integer altitude;
    private String description;
    private String typeOfOvernight;

    public DestinationServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeOfOvernight() {
        return typeOfOvernight;
    }

    public void setTypeOfOvernight(String typeOfOvernight) {
        this.typeOfOvernight = typeOfOvernight;
    }
}
