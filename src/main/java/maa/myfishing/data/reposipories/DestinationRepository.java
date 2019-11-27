package maa.myfishing.data.reposipories;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, String> {
    List<Destination> findAllByOrderByFishingsCountDesc();

    Optional<Destination> findByTownName(String townName);

    Optional<Destination> findById(String id);

    @Query("SELECT d from Destination d JOIN d.userInfos u where u.user.username = :username")
    List<Destination> findDestinationsByUsername(String username);

}
