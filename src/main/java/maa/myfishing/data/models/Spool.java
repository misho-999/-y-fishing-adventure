package maa.myfishing.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "spools")
public class Spool extends MainProperty {
    private SpoolSize spoolSize;

    public Spool() {
    }

    @Enumerated
    @Column(name = "spool_size", nullable = false, updatable = false)
    public SpoolSize getSpoolSize() {
        return spoolSize;
    }

    public void setSpoolSize(SpoolSize spoolSize) {
        this.spoolSize = spoolSize;
    }
}
