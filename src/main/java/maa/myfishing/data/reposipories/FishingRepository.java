package maa.myfishing.data.reposipories;

import maa.myfishing.data.models.Fishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FishingRepository extends JpaRepository<Fishing, String> {

    Optional<Fishing> findById(String id);

    Optional<Fishing> findByDateAndDescription(LocalDate date, String description);

    @Query("SELECT f FROM Fishing  f JOIN f.destination d where d.townName =:townName order by f.date desc")
    List<Fishing> findAllFishingByTownName(String townName);

//    @Query("FROM Fishing f LEFT JOIN f.destination d LEFT JOIN d.userInfos u where u.user.username =:username order by f.date desc")
//    List<Fishing> findAllFishingByUsername(String username);

    @Query("FROM Fishing f LEFT JOIN f.destination d where f.creator =:username and d.townName =:townName order by f.date desc")
    List<Fishing> findAllFishingsByUsernameAndTownName(String username, String townName);


    @Query("FROM Fishing f  WHERE f.creator =:username")
    List<Fishing> findAllFishingByUsername(String username);
}
