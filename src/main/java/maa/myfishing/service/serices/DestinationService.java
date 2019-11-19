package maa.myfishing.service.serices;

import maa.myfishing.service.models.DestinationServiceModel;
import org.springframework.stereotype.Service;


public interface DestinationService {

    DestinationServiceModel addDestination(DestinationServiceModel destinationServiceModel);

    DestinationServiceModel getDestinationByTownName(String townName);
}
