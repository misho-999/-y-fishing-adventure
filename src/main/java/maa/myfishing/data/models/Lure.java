package maa.myfishing.data.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "lures")
public class Lure extends BaseEntity {
    private String make;
    private String model;
    private String color;
    private TypeOfLure typeOfLure;
    private Integer weightInGrams;
    private Integer lengthInMillimeters;
    private Fishing fishing;

    public Lure() {
    }

    @Column(name = "make", nullable = false)
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "color", nullable = false, updatable = true)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = true)
    public TypeOfLure getTypeOfLure() {
        return typeOfLure;
    }

    public void setTypeOfLure(TypeOfLure typeOfLure) {
        this.typeOfLure = typeOfLure;
    }

    @Column(name = "weight_in_grams", nullable = false, updatable = true)
    @DecimalMin(value = "3")
    @DecimalMax(value = "20")
    public Integer getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(Integer weigth) {
        this.weightInGrams = weigth;
    }

    @Column(name = "length_in_mm", nullable = false, updatable = false)
    @Min(30)
    @Max(value = 200)
    public Integer getLengthInMillimeters() {
        return lengthInMillimeters;
    }

    public void setLengthInMillimeters(Integer length) {
        this.lengthInMillimeters = length;
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
