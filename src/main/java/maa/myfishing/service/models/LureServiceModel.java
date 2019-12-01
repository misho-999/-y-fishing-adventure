package maa.myfishing.service.models;

import maa.myfishing.data.models.TypeOfLure;

public class LureServiceModel {
    private String make;
    private String model;
    private String color;
    private TypeOfLure typeOfLure;
    private Integer weightInGrams;
    private Integer lengthInMillimeters;
    private String fishingUrl;

    public LureServiceModel() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TypeOfLure getTypeOfLure() {
        return typeOfLure;
    }

    public void setTypeOfLure(TypeOfLure typeOfLure) {
        this.typeOfLure = typeOfLure;
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
