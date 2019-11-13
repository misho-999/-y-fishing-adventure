package maa.myfishing.data.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private List<Destination> destinations;
    private List<UpcomingDestination> upcomingDestinations;

    public User() {
    }

    @NotNull
    @Size(min = 3, max = 10, message = "Invalid name") // message in Constants;
    @Column(name = "username", nullable = false, unique = true, updatable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "user")
    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    @OneToMany(mappedBy = "user")
    public List<UpcomingDestination> getUpcomingDestinations() {
        return upcomingDestinations;
    }

    public void setUpcomingDestinations(List<UpcomingDestination> upcomingDestinations) {
        this.upcomingDestinations = upcomingDestinations;
    }
}
