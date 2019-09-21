package myfish.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "spools")
public class Spool extends BaseEntity {
    private String make;
    private String model;

    public Spool() {
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
}
