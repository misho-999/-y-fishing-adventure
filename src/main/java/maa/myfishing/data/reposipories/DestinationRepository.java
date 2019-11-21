package maa.myfishing.data.reposipories;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, String> {

    Optional<Destination> findByTownName(String townName);

    Optional<Destination> findById(String id);

    List<Destination> findByUserInfos(UserInfo userInfo);
}
