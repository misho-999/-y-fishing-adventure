package maa.myfishing.data.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "upcoming_destinations")
public class UpcomingDestination extends BaseEntity {
    private String townName;
    private Integer population;
    private Integer altitude;
    private User user;

    public UpcomingDestination() {
    }

    @Column(name = "townName", nullable = false, unique = true)
    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    @Column(name = "population", nullable = false)
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Column(name = "altitude", nullable = false)
    @Min(0)
    @Max(2900)
    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
