//package maa.myfishing.web.api.controllers;
//
//import maa.myfishing.service.models.DestinationServiceModel;
//import maa.myfishing.service.serices.DestinationService;
//import maa.myfishing.service.serices.FishingService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class TopFIveApiController {
//    private final DestinationService destinationService;
//    private final FishingService fishingService;
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public TopFIveApiController(DestinationService destinationService, FishingService fishingService, ModelMapper modelMapper) {
//        this.destinationService = destinationService;
//        this.fishingService = fishingService;
//        this.modelMapper = modelMapper;
//    }
//
//    @GetMapping("/api/top-five")
//    List[] getTopFive() {
//        List<DestinationServiceModel> topFiveDestination = this.destinationService.getTopFiveDestination();
////        return this.fishingService.getTopFiveFishings();
//        return new List[]{topFiveDestination, topFiveDestination};
//    }
//}
