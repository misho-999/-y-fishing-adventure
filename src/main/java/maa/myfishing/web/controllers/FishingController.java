package maa.myfishing.web.controllers;

import maa.myfishing.service.models.FishingServiceModel;
import maa.myfishing.service.serices.FishingService;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.DestinationAllModel;
import maa.myfishing.web.models.FishingAddModel;
import maa.myfishing.web.models.FishingAllModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/fishings")
public class FishingController extends BaseController {
    private final FishingService fishingService;
    private final ModelMapper modelMapper;

    @Autowired
    public FishingController(FishingService fishingService, ModelMapper modelMapper) {
        this.fishingService = fishingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Fishing")
    public ModelAndView addFishing(@PathVariable String id, ModelAndView modelAndView) {
        return super.view("fishing/fishing-add.html");
    }

    @ModelAttribute(value = "fishingAddModel")
    public FishingAddModel fishingAddModel() {
        return new FishingAddModel();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView destinationAddConfirm(ModelAndView modelAndView, @ModelAttribute(name = "fishingAddModel")
            FishingAddModel fishingAddModel) {

        FishingServiceModel fishingServiceModel = this.modelMapper.map(fishingAddModel, FishingServiceModel.class);

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


    @GetMapping("/add-caught-fish")
    public ModelAndView AddCaughtFish(ModelAndView modelAndView) {
        return super.view("fishing/add-caught-fish.html");
    }
}
