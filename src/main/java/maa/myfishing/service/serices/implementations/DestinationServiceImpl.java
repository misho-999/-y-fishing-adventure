package maa.myfishing.service.serices.implementations;

import maa.myfishing.constants.Constants;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.data.reposipories.UserInfoRepository;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.serices.DestinationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;
    private final FishingRepository fishingRepository;
    private final UserInfoRepository userInfoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository, FishingRepository fishingRepository, UserInfoRepository userInfoRepository, ModelMapper modelMapper) {
        this.destinationRepository = destinationRepository;
        this.fishingRepository = fishingRepository;
        this.userInfoRepository = userInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DestinationServiceModel createDestination(DestinationServiceModel destinationServiceModel) {
        Destination destination = this.modelMapper.map(destinationServiceModel, Destination.class);

        return this.modelMapper.map(this.destinationRepository.saveAndFlush(destination), DestinationServiceModel.class);
    }

    @Override
    public DestinationServiceModel getDestinationByTownName(String townName) {

        Destination destination = this.destinationRepository.findByTownName(townName)
                .orElseThrow(() -> new DestinationNotFoundException(Constants.DESTINATION_WITH_TOWN_NAME_NOT_FOUND_EXCEPTION));

        return this.modelMapper.map(destination, DestinationServiceModel.class);
    }

    @Override
    public List<DestinationServiceModel> getAllDestinations() {
        return this.destinationRepository.findAllByOrderByFishingsCountDesc()
                .stream()
                .map(d -> this.modelMapper.map(d, DestinationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DestinationServiceModel> getMyDestinations(String username) {

        return this.destinationRepository.findDestinationsByUsername(username)
                .stream()
                .map(d -> this.modelMapper.map(d, DestinationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public DestinationServiceModel getDestinationById(String id) {
        Destination destination = this.destinationRepository.findById(id)
                .orElseThrow(() -> new DestinationNotFoundException(Constants.DESTINATION_WITH_TOWN_ID_NOT_FOUND_EXCEPTION));

        return this.modelMapper.map(destination, DestinationServiceModel.class);
    }

    @Override
    public DestinationServiceModel editDestination(String id, DestinationServiceModel destinationServiceModel) {
        Destination destination = this.destinationRepository.findById(id)
                .orElseThrow(() -> new DestinationNotFoundException(Constants.DESTINATION_WITH_TOWN_ID_NOT_FOUND_EXCEPTION));
        destination.setImgUrl(destinationServiceModel.getImgUrl());
        destination.setTownName(destinationServiceModel.getTownName());
        destination.setPopulation(destinationServiceModel.getPopulation());
        destination.setAltitude(destinationServiceModel.getAltitude());
        destination.setDescription(destinationServiceModel.getDescription());

        return this.modelMapper.map(this.destinationRepository.saveAndFlush(destination), DestinationServiceModel.class);
    }

    @Override
    public void deleteDestination(String id) {
        Destination destination = this.destinationRepository.findById(id)
                .orElseThrow(() -> new DestinationNotFoundException(Constants.DESTINATION_WITH_TOWN_ID_NOT_FOUND_EXCEPTION));

        List<Fishing> fishings = destination.getFishings();

        //Don't delet this code!!!!
//        fishings.forEach(fishing -> {
//            fishing.setDestination(null);
//        });

        fishings.forEach(fishing -> {
            fishing.setDestination(null);
            this.fishingRepository.delete(fishing);
            fishing.setFishes(null);
            fishing.setLures(null);
        });

        this.destinationRepository.delete(destination);
    }
}
