package maa.myfishing.web.models;

import java.time.LocalDate;

public class FishingAllViewModel {
    private String id;
    private String imgUrl;
    private LocalDate date;
    private String description;
    private String townName;
    private String creator;
    private Integer countOfFishes;
    private Integer countOfLures;

    public FishingAllViewModel() {
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

    public Integer getCountOfFishes() {
        return countOfFishes;
    }

    public void setCountOfFishes(Integer countOfFishes) {
        this.countOfFishes = countOfFishes;
    }

    public Integer getCountOfLures() {
        return countOfLures;
    }

    public void setCountOfLures(Integer countOfLures) {
        this.countOfLures = countOfLures;
    }
}
