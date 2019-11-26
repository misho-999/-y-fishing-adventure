package maa.myfishing.service.serices;

import maa.myfishing.service.models.FishingServiceModel;

import javax.validation.constraints.Max;
import java.util.List;

public interface FishingService {

    FishingServiceModel addFishingToDestination(FishingServiceModel fishingServiceModel, String destinationId);

    List<FishingServiceModel> getAllFishings();

    List<FishingServiceModel> getAllFishingsByTownName(String townName);

    List<FishingServiceModel> getAllFishingsByUsername(String username);

    FishingServiceModel getFishingById(String id);

}
