package maa.myfishing.service.models;

import java.time.LocalDate;

public class FishingServiceModel {
    private String id;
    private String imgUrl;
    private LocalDate date;
    private String description;
    private String townName;
    private String creator;
    private int countOfFishes;
    private int countOfLures;

    public FishingServiceModel() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getCountOfFishes() {
        return countOfFishes;
    }

    public void setCountOfFishes(int countOfFishes) {
        this.countOfFishes = countOfFishes;
    }

    public int getCountOfLures() {
        return countOfLures;
    }

    public void setCountOfLures(int countOfLures) {
        this.countOfLures = countOfLures;
    }
}
