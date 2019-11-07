package maa.myfishing.data.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MainProperty extends BaseEntity {
    private String make;
    private String model;

    @Column(name = "make", nullable = false, updatable = false)
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name = "model", nullable = false, updatable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
