package myfish.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "boats")
public class Boat {
    //Make	Model	Length	Weigth	width	motor	owner
    private String make;
    private String model;
    private Double length;
    private Double width;
    private Double weigth;
    private Integer engineHorsepower;
    private Fisherman fisherman;

    public Boat() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getWeigth() {
        return weigth;
    }

    public void setWeigth(Double weigth) {
        this.weigth = weigth;
    }

    public Integer getEngineHorsepower() {
        return engineHorsepower;
    }

    public void setEngineHorsepower(Integer engineHorsepower) {
        this.engineHorsepower = engineHorsepower;
    }

    @OneToOne
    @JoinColumn(name = "fisherman_id", referencedColumnName = "id")
    public Fisherman getFisherman() {
        return fisherman;
    }

    public void setFisherman(Fisherman owner) {
        this.fisherman = owner;
    }

}
