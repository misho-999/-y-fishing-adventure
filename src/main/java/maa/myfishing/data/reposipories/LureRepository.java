package maa.myfishing.data.reposipories;

import maa.myfishing.data.models.Lure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LureRepository extends JpaRepository<Lure, String> {

    Optional<Lure> findByMakeAndModelAndLengthInMillimeters(String make, String model, Integer lengthInMillimeters);

    @Query("FROM Lure l LEFT JOIN l.fishing f WHERE f.id =:fishingId ")
    List<Lure> findAllLuresByFishingId(String fishingId);

}
