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
    private String age;
    private List<Destination> destinations;
    private List<UpcomingDestination> upcomingDestinations;

    public User() {
    }

    @NotNull
    @Size(min = 3, max = 10, message = "Invalid name") // message in Constants;
    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "age", nullable = false)
    @NotNull
    @Min(8)
    @Max(100)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
