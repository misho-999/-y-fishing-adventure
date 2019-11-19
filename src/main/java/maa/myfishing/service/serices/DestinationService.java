package maa.myfishing.service.serices;

import maa.myfishing.service.models.DestinationServiceModel;

import java.util.List;


public interface DestinationService {

    DestinationServiceModel addDestination(DestinationServiceModel destinationServiceModel);

    DestinationServiceModel getDestinationByTownName(String townName);

    List<DestinationServiceModel> getAllDestinations();
}
