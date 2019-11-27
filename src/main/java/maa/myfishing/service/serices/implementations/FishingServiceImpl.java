package maa.myfishing.service.serices.implementations;

import maa.myfishing.constants.Constants;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fish;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.eroors.FishingAlreadyExistsException;
import maa.myfishing.eroors.FishingNotFoundException;
import maa.myfishing.service.models.FishServiceModel;
import maa.myfishing.service.models.FishingServiceModel;
import maa.myfishing.service.serices.FishingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FishingServiceImpl implements FishingService {
    private final FishingRepository fishingRepository;
    private final DestinationRepository destinationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FishingServiceImpl(FishingRepository fishingRepository, DestinationRepository destinationRepository, ModelMapper modelMapper) {
        this.fishingRepository = fishingRepository;
        this.destinationRepository = destinationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FishingServiceModel addFishingToDestination(FishingServiceModel fishingServiceModel, String destinationId) {
        Fishing fishing = this.fishingRepository
                .findByDateAndDescription(fishingServiceModel.getDate(), fishingServiceModel.getDescription())
                .orElse(null);

        if (fishing != null) {
            throw new FishingAlreadyExistsException(Constants.FISHING_ALREADY_EXIST_EXCEPTION);
        }

        fishing = this.modelMapper.map(fishingServiceModel, Fishing.class);

        Destination destination = this.destinationRepository.findById(destinationId)
                .orElseThrow(() -> new DestinationNotFoundException(Constants.DESTINATION_WITH_TOWN_ID_NOT_FOUND_EXCEPTION));

        destination.setFishingsCount(destination.getFishingsCount() + 1);
        this.destinationRepository.save(destination);

        fishing.setDestination(destination);

        this.fishingRepository.saveAndFlush(fishing);

        return this.modelMapper.map(fishing, FishingServiceModel.class);
    }

    @Override
    public List<FishingServiceModel> getAllFishings() {
        List<Fishing> allFishings = this.fishingRepository.findAll();

        return setTownName(allFishings);
    }

    @Override
    public List<FishingServiceModel> getAllFishingsByTownName(String townName) {
        List<Fishing> allFishings = this.fishingRepository.getAllFishingByTownName(townName);

        return this.setTownName(allFishings);
    }

    @Override
    public List<FishingServiceModel> getAllFishingsByUsername(String username) {
        List<Fishing> allFishings = this.fishingRepository.getAllFishingByUsername(username);

        return setTownName(allFishings);
    }

    @Override
    public FishingServiceModel getFishingById(String id) {
        Fishing fishing = this.fishingRepository.findById(id)
                .orElseThrow(() -> new FishingNotFoundException(Constants.FISHING_WITH_ID_NOT_FOUND_EXCEPTION));

        return this.modelMapper.map(fishing, FishingServiceModel.class);
    }

    private List<FishingServiceModel> setTownName(List<Fishing> fishings) {
        List<FishingServiceModel> fishingServiceModels = fishings.stream()
                .map(f -> this.modelMapper.map(f, FishingServiceModel.class))
                .collect(Collectors.toList());

        for (int i = 0; i < fishings.size(); i++) {
            fishingServiceModels.get(i).setTownName(fishings.get(i).getDestination().getTownName());
        }

        return fishingServiceModels;
    }
}
