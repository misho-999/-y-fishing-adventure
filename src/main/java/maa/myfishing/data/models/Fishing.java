package maa.myfishing.data.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "fishings")
public class Fishing extends BaseEntity {
    private LocalDate date;// На BindingModela трябва да има @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String description;
    private Boat boat;
    private Destination destination;
    private List<Fish> fishes;
    private List<Lure> lures;

    public Fishing() {
    }


    @Column(name = "date", nullable = false, unique = true)// На BindingModela трябва да има @DataTiemFormater(pattern = "yyyy-MM-dd")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(mappedBy = "fishing")
    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    @ManyToOne
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @OneToMany(mappedBy = "fishing")
    public List<Fish> getFishes() {
        return fishes;
    }

    public void setFishes(List<Fish> fishes) {
        this.fishes = fishes;
    }

    @OneToMany(mappedBy = "fishing")
    public List<Lure> getLures() {
        return lures;
    }

    public void setLures(List<Lure> lures) {
        this.lures = lures;
    }
}
