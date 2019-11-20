package maa.myfishing.data.reposipories;

import maa.myfishing.data.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, String> {

    Optional<Destination> findByTownName(String townName);

    Optional<Destination> findById(String id);
}
