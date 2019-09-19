package myfish.domain.modules.service;

public class FishServiceModel {

    private String fishName;
    private String fishFamily;
    private Double weigthInKilograms;
    private Integer lengthInSentimeters;
    private String dam;

    public FishServiceModel() {
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public String getFishFamily() {
        return fishFamily;
    }

    public void setFishFamily(String fishFamily) {
        this.fishFamily = fishFamily;
    }

    public Double getWeigthInKilograms() {
        return weigthInKilograms;
    }

    public void setWeigthInKilograms(Double weigthInKilograms) {
        this.weigthInKilograms = weigthInKilograms;
    }

    public Integer getLengthInSentimeters() {
        return lengthInSentimeters;
    }

    public void setLengthInSentimeters(Integer lengthInSentimeters) {
        this.lengthInSentimeters = lengthInSentimeters;
    }

    public String getDam() {
        return dam;
    }

    public void setDam(String dam) {
        this.dam = dam;
    }
}
