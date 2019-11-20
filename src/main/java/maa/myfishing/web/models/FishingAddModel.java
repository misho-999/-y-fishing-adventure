package maa.myfishing.web.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class FishingAddModel {
    private LocalDate date;// На BindingModela трябва да има @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
