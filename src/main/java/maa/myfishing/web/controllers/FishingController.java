package maa.myfishing.web.controllers;

import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.eroors.FishingAlreadyExistsException;
import maa.myfishing.service.models.FishingServiceModel;
import maa.myfishing.service.serices.CloudinaryService;
import maa.myfishing.service.serices.FishingService;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.FishingAddModel;
import maa.myfishing.web.models.FishingAllModel;
import org.apache.catalina.authenticator.SavedRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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

    @GetMapping("/create/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Fishing")
    public ModelAndView addFishing(@PathVariable String id, ModelAndView modelAndView) {
        return super.view("fishing/fishing-add.html");
    }

    @ModelAttribute(value = "fishingAddModel")
    public FishingAddModel fishingAddModel() {
        return new FishingAddModel();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView destinationAddConfirm(ModelAndView modelAndView, @ModelAttribute(name = "fishingAddModel")
            FishingAddModel fishingAddModel) throws IOException {

        FishingServiceModel fishingServiceModel = this.modelMapper.map(fishingAddModel, FishingServiceModel.class);

        fishingServiceModel.setImgUrl(this.cloudinaryService.uploadImage(fishingAddModel.getImage()));

        String destinationId = fishingAddModel.getDestinationId().replace("http://localhost:8000/fishings/add/", "");

        this.fishingService.addFishingToDestination(fishingServiceModel, destinationId);

        return super.redirect("/fishings/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("All Fishings")
    public ModelAndView all(ModelAndView modelAndView) {
        modelAndView.addObject("fishings", this.fishingService.getAllFishings()
                .stream()
                .map(p -> this.modelMapper.map(p, FishingAllModel.class))
                .collect(Collectors.toList()));

        return super.view("fishing/fishing-all.html", modelAndView);
    }

    @GetMapping("/all-for-destination/{townName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("All Fishings")
    public ModelAndView allForDestination(@PathVariable String townName, ModelAndView modelAndView) {
        modelAndView.addObject("fishings", this.fishingService.getAllFishingsByTownName(townName)
                .stream()
                .map(p -> this.modelMapper.map(p, FishingAllModel.class))
                .collect(Collectors.toList()));

        return super.view("fishing/fishing-all.html", modelAndView);
    }


    @GetMapping("/add-caught-fish")
    public ModelAndView AddCaughtFish(ModelAndView modelAndView) {
        return super.view("fishing/add-caught-fish.html");
    }

    //==========================================================================
    @ExceptionHandler({FishingAlreadyExistsException.class})
    public ModelAndView handleDestinationNotFound(DestinationNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error.html");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("statusCode", e.getStatusCode());

        return modelAndView;
    }
}
