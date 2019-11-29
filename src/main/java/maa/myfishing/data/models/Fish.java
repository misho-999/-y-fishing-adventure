package maa.myfishing.data.models;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "fishes")
public class Fish extends BaseEntity {

    private String fishName;
    private Double weightInKilograms;
    private Integer lengthInCentimeters;
    private Fishing fishing;

    public Fish() {
    }



    @Column(name = "name", nullable = false, updatable = false)
    public String getFishName() {
        return fishName;
    }

    public void setFishName(String name) {
        this.fishName = name;
    }

    @Column(name = "weigth_in_kg", nullable = false, updatable = false)
    @DecimalMin(value = "0.10")
    @DecimalMax(value = "200.00")
    public Double getWeightInKilograms() {
        return weightInKilograms;
    }

    public void setWeightInKilograms(Double weigth) {
        this.weightInKilograms = weigth;
    }

    @Column(name = "length_in_sm", nullable = false, updatable = false)
    @Min(20)
    @Max(value = 300)
    public Integer getLengthInCentimeters() {
        return lengthInCentimeters;
    }

    public void setLengthInCentimeters(Integer length) {
        this.lengthInCentimeters = length;
    }


    @ManyToOne
    @JoinColumn(name = "fishing_id", referencedColumnName = "id")
    public Fishing getFishing() {
        return fishing;
    }

    public void setFishing(Fishing fishing) {
        this.fishing = fishing;
    }
}
