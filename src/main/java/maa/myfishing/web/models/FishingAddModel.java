package maa.myfishing.web.models;

import maa.myfishing.constants.Constants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class FishingAddModel {
    private MultipartFile image;
    private LocalDate date;// На BindingModela трябва да има @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String description;
    private String destinationId;

    public FishingAddModel() {
    }

//    public String getImgUrl() {
//        return imgUrl;
//    }
//
//    public void setImgUrl(String imgUrl) {
//        this.imgUrl = imgUrl;
//    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
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
