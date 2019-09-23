package myfish.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "fishing_rods")
public class FishingRod extends MainProperty {
    private Double length;
    private Double weigth;
    private Fisherman fisherman;

    public FishingRod() {
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWeigth() {
        return weigth;
    }

    public void setWeigth(Double weigth) {
        this.weigth = weigth;
    }

   @ManyToOne
   @JoinColumn(name = "fisherman_id", referencedColumnName = "id")
    public Fisherman getFisherman() {
        return fisherman;
    }

    public void setFisherman(Fisherman fisherman) {
        this.fisherman = fisherman;
    }
}
