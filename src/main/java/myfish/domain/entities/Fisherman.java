package myfish.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "fishermans")
public class Fisherman extends BaseEntity {
    private String firstName;
    private String lastName;
    private String age;
    private Boat boat;
    private List<Lure> lures;
    private List<FishingRod> fishingRods;

    public Fisherman() {
    }

    @NotNull
    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age", nullable = false)
    @NotNull
    @Min(8)
    @Max(100)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @OneToOne
    @JoinColumn(name = "boat_id", referencedColumnName = "id")
    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    @OneToMany(mappedBy = "owner")
    public List<Lure> getLures() {
        return lures;
    }

    public void setLures(List<Lure> lures) {
        this.lures = lures;
    }

    @OneToMany(mappedBy = "fisherman")
    public List<FishingRod> getFishingRods() {
        return fishingRods;
    }

    public void setFishingRods(List<FishingRod> fishingRods) {
        this.fishingRods = fishingRods;
    }
}
