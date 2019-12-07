package maa.myfishing.service.models;

public class FishServiceModel {
    private String id;
    private String fishName;
    private Double weightInKilograms;
    private Integer lengthInCentimeters;
    private String creator;
    private String fishingId;

    public FishServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getFishingId() {
        return fishingId;
    }

    public void setFishingId(String fishingId) {
        this.fishingId = fishingId;
    }
}
