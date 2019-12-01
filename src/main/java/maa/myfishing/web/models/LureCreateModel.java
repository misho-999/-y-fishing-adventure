package maa.myfishing.web.models;

import maa.myfishing.data.models.TypeOnLure;

public class LureCreateModel {
    private String color;
    private TypeOnLure typeOnLure;
    private Integer weightInGrams;
    private Integer lengthInMillimeters;
    private String fishingUrl;

    public LureCreateModel() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TypeOnLure getTypeOnLure() {
        return typeOnLure;
    }

    public void setTypeOnLure(TypeOnLure typeOnLure) {
        this.typeOnLure = typeOnLure;
    }

    public Integer getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(Integer weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    public Integer getLengthInMillimeters() {
        return lengthInMillimeters;
    }

    public void setLengthInMillimeters(Integer lengthInMillimeters) {
        this.lengthInMillimeters = lengthInMillimeters;
    }

    public String getFishingUrl() {
        return fishingUrl;
    }

    public void setFishingUrl(String fishingUrl) {
        this.fishingUrl = fishingUrl;
    }
}
