package maa.myfishing.web.models;


public class DestinationCreateModel {
    private String creator;
    private String imgUrl;
    private String townName;
    private Integer population;
    private Integer altitude;
    private String description;
    private String typeOfOvernight;

    public DestinationCreateModel() {
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
