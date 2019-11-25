package maa.myfishing.data.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destinations")
public class Destination extends BaseEntity {
    private String creator;
    private String imgUrl;
    private String townName;
    private Integer population;
    private Integer altitude;
    private String description;
    private int fishingsCount;
    private List<UserInfo> userInfos;
    private List<Fishing> fishings;

    public Destination() {
        this.userInfos = new ArrayList<>();
        this.fishings = new ArrayList<>();
    }

    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(name = "img_url", unique = true)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
    @Min(value = 0, message = "Value should be more than 0")
    @Max(value = 5000 , message = "Value should be less than 5000")
    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "fishings_count")
    public int getFishingsCount() {
        return fishingsCount;
    }

    public void setFishingsCount(int fishingsCount) {
        this.fishingsCount = fishingsCount;
    }

    @ManyToMany
    @JoinTable(name = "destinations_users_infos",
            joinColumns = @JoinColumn(name = "destination_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_info_id"))
    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    @OneToMany(mappedBy = "destination")
    public List<Fishing> getFishings() {
        return fishings;
    }

    public void setFishings(List<Fishing> fishings) {
        this.fishings = fishings;
    }
}
