package maa.myfishing.data.reposipories;

import maa.myfishing.data.models.Destination;
import maa.myfishing.service.models.DestinationServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, String> {

    Destination findByTownName(String townName);
}
