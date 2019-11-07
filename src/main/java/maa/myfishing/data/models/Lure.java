package maa.myfishing.data.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "lures")
public class Lure extends MainProperty {
    private String color;
    private TypeOnLure typeOnLure;
    private Integer weigtInGrams;
    private Integer lengthInMilimeters;
    private Fisherman owner;

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
    public Integer getWeigtInGrams() {
        return weigtInGrams;
    }

    public void setWeigtInGrams(Integer weigth) {
        this.weigtInGrams = weigth;
    }

    @Column(name = "length_in_mm", nullable = false, updatable = false)
    @Min(3)
    @Max(value = 20)
    public Integer getLengthInMilimeters() {
        return lengthInMilimeters;
    }

    public void setLengthInMilimeters(Integer length) {
        this.lengthInMilimeters = length;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public Fisherman getOwner() {
        return owner;
    }

    public void setOwner(Fisherman owner) {
        this.owner = owner;
    }
}
