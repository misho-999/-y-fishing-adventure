package maa.myfishing.service.models;

public class FishServiceModel {
    private String fishName;
    private Double weightInKilograms;
    private Integer lengthInCentimeters;
    private String fishingId;

    public FishServiceModel() {
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public Double getWeightInKilograms() {
        return weightInKilograms;
    }

    public void setWeightInKilograms(Double weightInKilograms) {
        this.weightInKilograms = weightInKilograms;
    }

    public Integer getLengthInCentimeters() {
        return lengthInCentimeters;
    }

    public void setLengthInCentimeters(Integer lengthInCentimeters) {
        this.lengthInCentimeters = lengthInCentimeters;
    }

    public String getFishingId() {
        return fishingId;
    }

    public void setFishingId(String fishingId) {
        this.fishingId = fishingId;
    }
}
