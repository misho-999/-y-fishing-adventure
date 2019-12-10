package maa.myfishing.web.api.models;

public class FishTopFiveViewModel {
    private String fishName;
    private Double weightInKilograms;
    private Integer lengthInCentimeters;
    private String creator;

    public FishTopFiveViewModel() {
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
}
