package maa.myfishing.data.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "lures")
public class Lure extends MainProperty {
    private String color;
    private TypeOnLure typeOnLure;
    private Integer weightInGrams;
    private Integer lengthInMillimeters;
    private Fishing fishing;

    public Lure() {
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
    public TypeOnLure getTypeOnLure() {
        return typeOnLure;
    }

    public void setTypeOnLure(TypeOnLure typeOnLure) {
        this.typeOnLure = typeOnLure;
    }

    @Column(name = "weigth_in_rgams", nullable = false, updatable = true)
    @DecimalMin(value = "0.10")
    @DecimalMax(value = "200.00")
    public Integer getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(Integer weigth) {
        this.weightInGrams = weigth;
    }

    @Column(name = "length_in_mm", nullable = false, updatable = false)
    @Min(3)
    @Max(value = 20)
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
