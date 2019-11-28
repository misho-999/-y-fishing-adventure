package maa.myfishing.web.models;

public class FishCreateModel {
    private String fishName;
    private double weightInKilograms;
    private int lengthInCentimeters;
    private String fishingId;
    private String fishingUrl;

    public FishCreateModel() {
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public double getWeightInKilograms() {
        return weightInKilograms;
    }

    public void setWeightInKilograms(double weightInKilograms) {
        this.weightInKilograms = weightInKilograms;
    }

    public int getLengthInCentimeters() {
        return lengthInCentimeters;
    }

    public void setLengthInCentimeters(int lengthInCentimeters) {
        this.lengthInCentimeters = lengthInCentimeters;
    }

    public String getFishingId() {
        return fishingId;
    }

    public void setFishingId(String fishingId) {
        this.fishingId = fishingId;
    }

    public String getFishingUrl() {
        return fishingUrl;
    }

    public void setFishingUrl(String fishingUrl) {
        this.fishingUrl = fishingUrl;
    }
}
