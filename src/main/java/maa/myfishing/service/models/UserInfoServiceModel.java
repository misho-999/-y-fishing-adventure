package maa.myfishing.service.models;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.User;

import java.util.List;

public class UserInfoServiceModel {
    private String id;
    private User user;
    private List<Destination> destinations;

    public UserInfoServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }
}
