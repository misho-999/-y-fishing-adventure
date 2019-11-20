package maa.myfishing.service.serices;

import maa.myfishing.service.models.FishingServiceModel;

public interface FishingService {

    FishingServiceModel addFishingToDestination(FishingServiceModel fishingServiceModel, String destinationId);

}
