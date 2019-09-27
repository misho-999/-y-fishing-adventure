package myfish.domain.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "destinations")
public class Destination extends BaseEntity {
    private String townName;
    private String region;
    private Integer population;
    private Integer altitude;
    private List<Fisherman> fishermens;

    public Destination() {
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    @ManyToMany(mappedBy = "destinations")
    public List<Fisherman> getFishermens() {
        return fishermens;
    }

    public void setFishermens(List<Fisherman> fishermens) {
        this.fishermens = fishermens;
    }
}
