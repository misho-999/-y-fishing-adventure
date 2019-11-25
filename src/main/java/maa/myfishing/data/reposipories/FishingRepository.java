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
    Optional<Fishing> findByDateAndDescription(LocalDate date, String description);

    @Query("SELECT f FROM Fishing  f JOIN f.destination d where d.townName =:townName")
    List<Fishing> getAllFishingByTownName(String townName);

    @Query("FROM Fishing f LEFT JOIN f.destination d LEFT JOIN d.userInfos u where u.user.username =:username ")
    List<Fishing> getAllFishingByUsername(String username);

}
