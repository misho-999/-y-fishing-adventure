package myfish.domain.modules.binding;

public class FishRegisterBindingModel {

    private String fishName;
    private String firshFamily;
    private Double weigthInKilograms;
    private Integer lengthInSentimeters;
    private String dam;

    public FishRegisterBindingModel() {
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public String getFirshFamily() {
        return firshFamily;
    }

    public void setFirshFamily(String firshFamily) {
        this.firshFamily = firshFamily;
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
