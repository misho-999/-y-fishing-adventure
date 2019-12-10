package maa.myfishing.web.api.controllers;

import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.models.FishServiceModel;
import maa.myfishing.service.models.FishingServiceModel;
import maa.myfishing.service.serices.DestinationService;
import maa.myfishing.service.serices.FishService;
import maa.myfishing.service.serices.FishingService;
import maa.myfishing.web.api.models.DestinationTopFiveViewModel;
import maa.myfishing.web.api.models.FishTopFiveViewModel;
import maa.myfishing.web.api.models.FishingTopFiveViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class TopFIveApiController {
    private final DestinationService destinationService;
    private final FishingService fishingService;
    private final FishService fishService;
    private final ModelMapper modelMapper;

    @Autowired
    public TopFIveApiController(DestinationService destinationService, FishingService fishingService, FishService fishService, ModelMapper modelMapper) {
        this.destinationService = destinationService;
        this.fishingService = fishingService;
        this.fishService = fishService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/top-five/destinations")
    List<DestinationTopFiveViewModel> topFiveDestinations() {
        return this.destinationService.getTopFiveDestination()
                .stream()
                .map(d -> this.modelMapper.map(d, DestinationTopFiveViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/top-five/fishings")
    List<FishingTopFiveViewModel> topFiveFishings() {
        return this.fishingService.getTopFiveFishings()
                .stream()
                .map(f -> this.modelMapper.map(f, FishingTopFiveViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/top-five/fishes")
    List<FishTopFiveViewModel> topFiveFishes() {
        return this.fishService.getTopFiveFishes()
                .stream()
                .map(f -> this.modelMapper.map(f, FishTopFiveViewModel.class))
                .collect(Collectors.toList());
    }
}
