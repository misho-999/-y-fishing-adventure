package myfish.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "fishes")
public class Fish extends BaseEntity {

    private String fishName;
    private String fishFamily;
    private Double weigthInKilograms;
    private Integer lengthInSentimeters;
    private String dam;
    private Fisherman fisherman;

    public Fish() {
    }

    @Column(name = "name", nullable = false, updatable = false)
    public String getFishName() {
        return fishName;
    }

    public void setFishName(String name) {
        this.fishName = name;
    }

    @Column(name = "family", nullable = false, updatable = false)
    public String getFishFamily() {
        return fishFamily;
    }

    public void setFishFamily(String firshFamily) {
        this.fishFamily = firshFamily;
    }

    @Column(name = "weigth_in_kg", nullable = false, updatable = false)
    @DecimalMin(value = "0.10")
    @DecimalMax(value = "200.00")
    public Double getWeigthInKilograms() {
        return weigthInKilograms;
    }

    public void setWeigthInKilograms(Double weigth) {
        this.weigthInKilograms = weigth;
    }

    @Column(name = "length_in_sm", nullable = false, updatable = false)
    @Min(20)
    @Max(value = 300)
    public Integer getLengthInSentimeters() {
        return lengthInSentimeters;
    }

    public void setLengthInSentimeters(Integer length) {
        this.lengthInSentimeters = length;
    }

    public String getDam() {
        return dam;
    }

    public void setDam(String dam) {
        this.dam = dam;
    }

    @ManyToOne
    @JoinColumn(name = "fish_id", referencedColumnName = "id")
    public Fisherman getFisherman() {
        return fisherman;
    }

    public void setFisherman(Fisherman fisherman) {
        this.fisherman = fisherman;
    }

}
