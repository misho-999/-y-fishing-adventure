package maa.myfishing.service.serices;

import maa.myfishing.service.models.DestinationServiceModel;

import java.util.List;


public interface DestinationService {

    DestinationServiceModel createDestination(DestinationServiceModel destinationServiceModel);

    DestinationServiceModel getDestinationById(String id);

    DestinationServiceModel getDestinationByTownName(String townName);

    List<DestinationServiceModel> getAllDestinations();

    List<DestinationServiceModel> getMyDestinations(String username);

    DestinationServiceModel editDestination(String id, DestinationServiceModel destinationServiceModel);

    void deleteDestination(String id);
}
