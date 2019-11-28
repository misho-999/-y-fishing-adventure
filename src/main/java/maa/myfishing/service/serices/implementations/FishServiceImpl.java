package maa.myfishing.service.serices.implementations;

import maa.myfishing.data.models.Fish;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.FishRepository;
import maa.myfishing.service.models.FishServiceModel;
import maa.myfishing.service.models.FishingServiceModel;
import maa.myfishing.service.serices.FishService;
import maa.myfishing.service.serices.FishingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;
    private final FishingService fishingService;
    private final ModelMapper modelMapper;

    @Autowired
    public FishServiceImpl(FishRepository fishRepository, FishingService fishingService, ModelMapper modelMapper) {
        this.fishRepository = fishRepository;
        this.fishingService = fishingService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createFish(FishServiceModel fishServiceModel) {

        if (fishServiceModel.getFishingId().equals("")) {
            fishServiceModel.setFishingId(fishServiceModel.getFishingUrl().replace("http://localhost:8000/fish/create/", ""));
        }

        Fish fish = this.modelMapper.map(fishServiceModel, Fish.class);

        FishingServiceModel fishingServiceModel = this.fishingService.getFishingById(fishServiceModel.getFishingId());

        Fishing fishing = this.modelMapper.map(fishingServiceModel, Fishing.class);

        fish.setFishing(fishing);

        this.fishRepository.saveAndFlush(fish);
    }

    @Override
    public List<FishServiceModel> getAllFishesByFishingId(String id) {

        List<Fish> allByFishingId = this.fishRepository.findAllByFishingId(id);

        return allByFishingId.stream()
                .map(f -> this.modelMapper.map(f, FishServiceModel.class))
                .collect(Collectors.toList());
    }
}
