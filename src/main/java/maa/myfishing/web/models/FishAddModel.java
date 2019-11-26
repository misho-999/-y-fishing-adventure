package maa.myfishing.web.models;

public class FishAddModel {
    private String fishName;
    private double weightInKilograms;
    private int lengthInCentimeters;
    private String fishingId;

    public FishAddModel() {
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
}
