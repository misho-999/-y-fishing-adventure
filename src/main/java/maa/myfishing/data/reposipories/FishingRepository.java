package maa.myfishing.data.reposipories;

import jdk.dynalink.linker.LinkerServices;
import maa.myfishing.data.models.Destination;
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

//    List<Fishing> getAllByDestination(Destination destination);

    //SELECT c1, c2 FROM Country c1 INNER JOIN c1.neighbors c2

//    @Query("SELECT f FROM Fishing f join f.destination d where  = :townName")
//    @Query(value = "SELECT * from fishings AS f\n" +
//            "JOIN destinations AS d\n" +
//            "ON f.destination_id = d.id", nativeQuery = true)


    @Query(value = "SELECT * from fishings AS f\n" +
            "JOIN destinations AS d\n" +
            "ON f.destination_id = d.id\n" +
            "WHERE d.town_name = ?;", nativeQuery = true)
    List<Fishing> getAllFishingByTownName(String townName);
}
