package maa.myfishing.data.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class UserInfo extends BaseEntity {
    private User user;
    private List<Destination> destinations;

    public UserInfo() {
        this.destinations = new ArrayList<>();
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(mappedBy = "userInfos", fetch = FetchType.EAGER)
    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }
}
