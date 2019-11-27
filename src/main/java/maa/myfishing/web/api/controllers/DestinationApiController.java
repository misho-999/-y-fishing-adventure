package maa.myfishing.web.api.controllers;

import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.serices.DestinationService;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.api.models.DestinationResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DestinationApiController {
    private final DestinationService destinationService;
    private final ModelMapper modelMapper;

    public DestinationApiController(DestinationService destinationService, ModelMapper modelMapper) {
        this.destinationService = destinationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/api/destinations")
    @PageTitle("All Destinations")
    public List [] allDestinations(Principal principal) {
        List <String> allDestinations = this.destinationService.getAllDestinations()
                .stream()
                .map(DestinationServiceModel::getTownName)
                .collect(Collectors.toList());

        List <String> myDestinations = this.destinationService.getMyDestinations(principal.getName())
                .stream()
                .map(DestinationServiceModel::getTownName)
                .collect(Collectors.toList());

        return new List[]{allDestinations, myDestinations};
    }

//    @PostMapping("/api/items/{id}")
//    public void BuyItem(@PathVariable long id, HttpSession session) {
//        String username = getUsername(session);
//        itemsService.createForUserById(id, username);
//    }

}
