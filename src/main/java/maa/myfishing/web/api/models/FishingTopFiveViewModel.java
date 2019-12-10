package maa.myfishing.web.api.models;

import java.time.LocalDate;

public class FishingTopFiveViewModel {
    private LocalDate date;
    private String townName;
    private String creator;
    private Integer countOfFishes;
    private Integer countOfLures;

    public FishingTopFiveViewModel() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
