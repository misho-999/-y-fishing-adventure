package maa.myfishing.data.reposipories;

import maa.myfishing.data.models.Lure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LureRepository extends JpaRepository<Lure, String> {

    Optional<Lure> findByMakeAndModelAndLengthInMillimeters(String make, String model, Integer lengthInMillimeters);

}
