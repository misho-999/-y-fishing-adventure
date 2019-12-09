package maa.myfishing.web.models.fishing;

import java.time.LocalDate;

public class FishingDetailsViewModel {
    private String imgUrl;
    private LocalDate date;
    private String description;
    private String creator;

    public FishingDetailsViewModel() {
    }

    public String getImage() {
        return imgUrl;
    }

    public void setImage(String image) {
        this.imgUrl = image;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
