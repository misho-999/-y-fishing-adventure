package maa.myfishing.data.reposipories;

import maa.myfishing.data.models.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FishRepository extends JpaRepository<Fish, String> {

    List<Fish> findAllByFishingIdOrderByWeightInKilogramsDesc(String id);

    Optional<Fish> findByFishNameAndWeightInKilogramsAndLengthInCentimeters(String id, double weightInKilograms, int lengthInCentimeters);

    List<Fish> findAllByOrderByWeightInKilogramsDesc();

    List<Fish> findTop5ByOrderByWeightInKilogramsDesc();
}
