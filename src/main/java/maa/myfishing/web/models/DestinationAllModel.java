package maa.myfishing.web.models;

public class DestinationAllModel {
    private String id;
    private String imgUrl;
    private String townName;
    private Integer population;
    private Integer altitude;
    private String description;
    private int countOfFishings;

    public DestinationAllModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getCountOfFishings() {
        return countOfFishings;
    }

    public void setCountOfFishings(int countOfFishings) {
        this.countOfFishings = countOfFishings;
    }
}
