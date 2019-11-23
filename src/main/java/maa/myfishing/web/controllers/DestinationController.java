package maa.myfishing.web.controllers;

import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.eroors.TownAlreadyExistException;
import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.serices.DestinationService;
import maa.myfishing.service.serices.FishingService;
import maa.myfishing.service.serices.UserInfoService;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.DestinationAddModel;
import maa.myfishing.web.models.DestinationAllModel;
import maa.myfishing.web.models.DestinationDetailsViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/destinations")
public class DestinationController extends BaseController {

    private final UserInfoService userInfoService;
    private final DestinationService destinationService;
    private final FishingService fishingService;
    private final ModelMapper modelMapper;

    @Autowired
    public DestinationController(UserInfoService userInfoService, DestinationService destinationService, FishingService fishingService, ModelMapper modelMapper) {
        this.userInfoService = userInfoService;
        this.destinationService = destinationService;
        this.fishingService = fishingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("All Destinations")
    public ModelAndView allDestinations(ModelAndView modelAndView) {
        modelAndView.addObject("destinations", this.destinationService.getAllDestinations()
                .stream()
                .map(p -> this.modelMapper.map(p, DestinationAllModel.class))
                .collect(Collectors.toList()));

        return super.view("destination/all-destinations.html", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("My Destinations")
    public ModelAndView myDestinations(ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("destinations", this.destinationService.getMyDestinations(principal.getName())
                .stream()
                .map(p -> this.modelMapper.map(p, DestinationAllModel.class))
                .collect(Collectors.toList()));

//        modelAndView.addObject("countOfFishings", this.fishingService.getCountOfFishingsByDestination());

        return super.view("destination/my-destinations.html", modelAndView);
    }

    //=======================================================================
    @GetMapping("/add-to-my/{townName}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Ad To My Destinations")
    public ModelAndView addToMyDestinations(@PathVariable String townName, ModelAndView modelAndView) {
        DestinationServiceModel destination =
                this.modelMapper.map(this.destinationService.getDestinationByTownName(townName), DestinationServiceModel.class);

        modelAndView.addObject("destination", destination);

        return super.view("destination/add-to-my-destination.html", modelAndView);
    }

    @PostMapping("/add-to-my/{townName}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addToMyDestinationsConfirm(@PathVariable String townName
            , @ModelAttribute DestinationAddModel destinationAddModel, Principal principal) {

        this.userInfoService.addDestination(townName, principal.getName());

        return super.redirect("/destinations/my");
    }


    //=======================================================================
    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Add Destination")
    public ModelAndView add(ModelAndView modelAndView) {
        return super.view("destination/create-destination.html");
    }

//    @ModelAttribute(value = "destinationModel")
//    public DestinationAddModel destinationAddModel() {
//        return new DestinationAddModel();
//    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView destinationAddConfirm(ModelAndView modelAndView, @ModelAttribute(name = "destinationModel")
            DestinationAddModel destinationAddModel, Principal principal) {

        DestinationServiceModel destinationServiceModel = this.modelMapper.map(destinationAddModel, DestinationServiceModel.class);

        this.destinationService.createDestination(destinationServiceModel);

        this.userInfoService.addDestination(destinationServiceModel.getTownName(), principal.getName());

        return super.redirect("/destinations/my");
//        return super.redirect("/destinations/my");
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Destination Details")
    public ModelAndView destinationDetails(@PathVariable String id, ModelAndView modelAndView) {
        DestinationDetailsViewModel destination =
                this.modelMapper.map(this.destinationService.getDestinationById(id), DestinationDetailsViewModel.class);

        modelAndView.addObject("destination", destination);

        return super.view("destination/details-destination.html", modelAndView);
    }



    @GetMapping("/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Edit Destination")
    public ModelAndView editProduct(@PathVariable String id, ModelAndView modelAndView) {
        DestinationServiceModel destinationServiceModel = this.destinationService.getDestinationById(id);
        DestinationAddModel destinationAddModel = this.modelMapper.map(destinationServiceModel, DestinationAddModel.class);

        modelAndView.addObject("destination", destinationServiceModel);
        modelAndView.addObject("destinationId", id);

        return super.view("destination/edit-destination", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProductConfirm(@PathVariable String id, @ModelAttribute DestinationAddModel destinationAddModel) {
        this.destinationService.editDestination(id, this.modelMapper.map(destinationAddModel, DestinationServiceModel.class));

//        return super.redirect("/destinations/details/" + id);
        return super.redirect("/destinations/my");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Delete Destination")
    public ModelAndView deleteProduct(@PathVariable String id, ModelAndView modelAndView) {
        DestinationServiceModel destinationServiceModel = this.destinationService.getDestinationById(id);
        DestinationAddModel destinationAddModel = this.modelMapper.map(destinationServiceModel, DestinationAddModel.class);
//        model.setCategories(productServiceModel.getCategories().stream().map(c -> c.getName()).collect(Collectors.toList()));

        modelAndView.addObject("destination", destinationAddModel);
        modelAndView.addObject("destinationId", id);

        return super.view("destination/delete-destination", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView deleteProductConfirm(@PathVariable String id) {
        this.destinationService.deleteDestination(id);

        return super.redirect("/destinations/my");
    }

    @ExceptionHandler({DestinationNotFoundException.class, TownAlreadyExistException.class})
    public ModelAndView handleDestinationNotFound(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error.html");
        modelAndView.addObject("message", e.getMessage());
//        modelAndView.addObject("statusCode", e.getStatusCode());

        return modelAndView;
    }
}
