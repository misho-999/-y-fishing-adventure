package maa.myfishing.data.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "fishings")
public class Fishing extends BaseEntity {
    private String imgUrl;
    private LocalDate date;// На BindingModel трябва да има @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String description;
    private Destination destination;
    private String creator;
    private List<Fish> fishes;
    private List<Lure> lures;

    public Fishing() {
    }

    @Column(name = "img_url", unique = true)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Column(name = "date", nullable = false)// На BindingModela трябва да има @DataTiemFormater(pattern = "yyyy-MM-dd")
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

    @Column(name = "creator", nullable = false)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @ManyToOne
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @OneToMany(mappedBy = "fishing", cascade = CascadeType.ALL)
    public List<Fish> getFishes() {
        return fishes;
    }

    public void setFishes(List<Fish> fishes) {
        this.fishes = fishes;
    }

    @OneToMany(mappedBy = "fishing", cascade = CascadeType.ALL)
    public List<Lure> getLures() {
        return lures;
    }

    public void setLures(List<Lure> lures) {
        this.lures = lures;
    }
}
