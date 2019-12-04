package maa.myfishing.service.serices.implementations;

import maa.myfishing.constants.validation.DestinationValidationConstants;
import maa.myfishing.constants.validation.FishingValidationConstants;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.data.reposipories.LureRepository;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.eroors.FishingAlreadyExistsException;
import maa.myfishing.eroors.FishingNotFoundException;
import maa.myfishing.service.models.FishingServiceModel;
import maa.myfishing.service.serices.FishingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FishingServiceImpl implements FishingService {
    private final FishingRepository fishingRepository;
    private final FishRepository fishRepository;
    private final LureRepository lureRepository;
    private final DestinationRepository destinationRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public FishingServiceImpl(FishingRepository fishingRepository, FishRepository fishRepository, LureRepository lureRepository, DestinationRepository destinationRepository, Validator validator, ModelMapper modelMapper) {
        this.fishingRepository = fishingRepository;
        this.fishRepository = fishRepository;
        this.lureRepository = lureRepository;
        this.destinationRepository = destinationRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public FishingServiceModel addFishingToDestination(FishingServiceModel fishingServiceModel, String townName) {
        if (!validator.validate(fishingServiceModel).isEmpty()) {
            throw new IllegalArgumentException("Invalid Fishing");
        }

        Fishing fishing = this.fishingRepository
                .findByDateAndDescription(fishingServiceModel.getDate(), fishingServiceModel.getDescription())
                .orElse(null);

        if (fishing != null) {
            throw new FishingAlreadyExistsException(FishingValidationConstants.FISHING_ALREADY_EXIST_EXCEPTION);
        }

        fishing = this.modelMapper.map(fishingServiceModel, Fishing.class);

        Destination destination = this.destinationRepository.findByTownName(townName)
                .orElseThrow(() -> new DestinationNotFoundException(DestinationValidationConstants.DESTINATION_WITH_TOWN_ID_NOT_FOUND_EXCEPTION));

        int fishingCount = fishingRepository.findAllFishingByTownName(destination.getTownName()).size();

        destination.setFishingsCount(fishingCount + 1);

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
        List<Fishing> allFishings = this.fishingRepository.findAllFishingByTownName(townName);

        return this.setTownName(allFishings);
    }

    @Override
    public List<FishingServiceModel> getAllFishingsByUsername(String username) {
        List<Fishing> allFishings = this.fishingRepository.findAllFishingByUsername(username);

        return setTownName(allFishings);
    }


    @Override
    public List<FishingServiceModel> getAllFishingsByUsernameAndTownName(String username, String townName) {
        List<Fishing> allFishings = this.fishingRepository.findAllFishingsByUsernameAndTownName(username, townName);

        List<FishingServiceModel> fishings = this.setTownName(allFishings);

        for (FishingServiceModel fishing : fishings) {
            fishing.setCountOfFishes(this.fishRepository.findAllByFishingIdOrderByWeightInKilogramsDesc(fishing.getId()).size());
            fishing.setCountOfLures(this.lureRepository.findAllLuresByFishingId(fishing.getId()).size());
        }

        return fishings;
    }

    @Override
    public FishingServiceModel getFishingById(String id) {
        Fishing fishing = this.fishingRepository.findById(id)
                .orElseThrow(() -> new FishingNotFoundException(FishingValidationConstants.FISHING_WITH_ID_NOT_FOUND_EXCEPTION));

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

    @Override
    public void deleteFishing(String id, String townName) {
        Fishing fishing = this.fishingRepository.findById(id)
                .orElseThrow(() -> new FishingNotFoundException(FishingValidationConstants.FISHING_WITH_ID_NOT_FOUND_EXCEPTION));

        Destination destination = destinationRepository.findByTownName(townName)
                .orElseThrow(() -> new DestinationNotFoundException(
                        DestinationValidationConstants.DESTINATION_WITH_TOWN_NAME_NOT_FOUND_EXCEPTION));

        destination.setFishingsCount(destination.getFishingsCount() - 1);
        this.destinationRepository.save(destination);

        this.fishingRepository.delete(fishing);
    }
}
