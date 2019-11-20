package maa.myfishing.service.models;

import java.time.LocalDate;

public class FishingServiceModel {
    private LocalDate date;
    private String description;

    public FishingServiceModel() {
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
}
