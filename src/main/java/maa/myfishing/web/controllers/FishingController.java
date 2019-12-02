package maa.myfishing.web.controllers;

import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.eroors.FishingAlreadyExistsException;
import maa.myfishing.service.models.FishingServiceModel;
import maa.myfishing.service.serices.CloudinaryService;
import maa.myfishing.service.serices.FishingService;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.FishingCreateModel;
import maa.myfishing.web.models.FishingAllModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/fishings")
public class FishingController extends BaseController {
    private final FishingService fishingService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @Autowired
    public FishingController(FishingService fishingService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.fishingService = fishingService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create/{townName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Fishing")
    public ModelAndView addFishing(@PathVariable String townName, ModelAndView modelAndView) {
        modelAndView.addObject("townName", townName);

        return super.view("fishing/create-fishing.html", modelAndView);
    }

    @ModelAttribute(value = "fishingAddModel")
    public FishingCreateModel fishingAddModel() {
        return new FishingCreateModel();
    }

    @PostMapping("/create/{townName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView destinationAddConfirm(@PathVariable String townName, ModelAndView modelAndView, @ModelAttribute(name = "fishingAddModel")
            FishingCreateModel fishingCreateModel) throws IOException {

        FishingServiceModel fishingServiceModel = this.modelMapper.map(fishingCreateModel, FishingServiceModel.class);

        fishingServiceModel.setImgUrl(this.cloudinaryService.uploadImage(fishingCreateModel.getImage()));

        this.fishingService.addFishingToDestination(fishingServiceModel, townName);

        return super.redirect("/fishings/all-my-for-destination/" + townName);
    }


    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("All Fishings")
    public ModelAndView all(ModelAndView modelAndView) {
        modelAndView.addObject("fishings", this.fishingService.getAllFishings()
                .stream()
                .map(p -> this.modelMapper.map(p, FishingAllModel.class))
                .collect(Collectors.toList()));

        return super.view("fishing/all-fishing.html", modelAndView);
    }


    @GetMapping("/all-for-destination/{townName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("All Fishings")
    public ModelAndView allForDestination(@PathVariable String townName, ModelAndView modelAndView) {
        modelAndView.addObject("fishings", this.fishingService.getAllFishingsByTownName(townName)
                .stream()
                .map(p -> this.modelMapper.map(p, FishingAllModel.class))
                .collect(Collectors.toList()));
        modelAndView.addObject("townName", townName);

        return super.view("fishing/all-for-destination-fishings.html", modelAndView);
    }


    @GetMapping("/all-my-for-destination/{townName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("All Fishings")
    public ModelAndView allMyForDestination(@PathVariable String townName, ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("fishings", this.fishingService.getAllFishingsByUsernameAndTownName(principal.getName(), townName)
                .stream()
                .map(p -> this.modelMapper.map(p, FishingAllModel.class))
                .collect(Collectors.toList()));
        modelAndView.addObject("townName", townName);

        return super.view("fishing/all-my-for-destination-fishings.html", modelAndView);
    }


    @GetMapping("/add-caught-fish")
    public ModelAndView AddCaughtFish(ModelAndView modelAndView) {
        return super.view("fishing/create-fish.html");
    }


    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("My Fishings")
    public ModelAndView allMyFishings(ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("fishings", this.fishingService.getAllFishingsByUsername(principal.getName())
                .stream()
                .map(p -> this.modelMapper.map(p, FishingAllModel.class))
                .collect(Collectors.toList()));

        return super.view("fishing/my-fishing.html", modelAndView);
    }

    //==========================================================================
    @ExceptionHandler({FishingAlreadyExistsException.class})
    public ModelAndView handleDestinationNotFound(DestinationNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error.html");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("statusCode", e.getStatusCode());

        return modelAndView;
    }

//    @PostMapping("/delete/{id}")
//    @PreAuthorize("isAuthenticated()")
//    public ModelAndView deleteFishingConfirm(@PathVariable String id) {
//        this.fishingService.deleteFishing(id);
//
//        return super.redirect("/destinations/my");
//    }
}
