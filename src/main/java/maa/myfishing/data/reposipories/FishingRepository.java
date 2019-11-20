package maa.myfishing.data.reposipories;

import maa.myfishing.data.models.Fishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FishingRepository extends JpaRepository<Fishing, String> {
    Optional<Fishing> findByDateAndDescription(LocalDate date, String description);
}
