package maa.myfishing.web.controllers;


import maa.myfishing.service.models.FishServiceModel;
import maa.myfishing.service.serices.FishService;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.FishAddModel;
import maa.myfishing.web.models.FishAllModel;
import maa.myfishing.web.models.FishingAllModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/fish")
public class FishController extends BaseController {
    private final FishService fishService;
    private final ModelMapper modelMapper;

    @Autowired
    public FishController(FishService fishService, ModelMapper modelMapper) {
        this.fishService = fishService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Fish")
    public ModelAndView addFishing(@PathVariable String id) {
        return super.view("fish/add-caught-fish.html");
    }


    @ModelAttribute(value = "fishModel")
    public FishAddModel fishAddModel() {
        return new FishAddModel();
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView fishCreateConfirm(ModelAndView modelAndView, @ModelAttribute(name = "fishModel")
            FishAddModel fishAddModel) {

        FishServiceModel fishServiceModel = this.modelMapper.map(fishAddModel, FishServiceModel.class);

        this.fishService.createFish(fishServiceModel);

        return super.redirect("/fishings/all");
    }

    @GetMapping("/all-for-fishing/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("All Fish For Destination")
    public ModelAndView allForDestination(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("fishes", this.fishService.getAllFishesByFishingId(id    )
                .stream()
                .map(f -> this.modelMapper.map(f, FishAllModel.class))
                .collect(Collectors.toList()));

        return super.view("fish/fish-all-for-fishing.html", modelAndView);
    }


//    @ExceptionHandler({DestinationNotFoundException.class, TownAlreadyExistException.class})
//    public ModelAndView handleDestinationNotFound(Exception e) {
//        ModelAndView modelAndView = new ModelAndView("error.html");
//        modelAndView.addObject("message", e.getMessage());
////        modelAndView.addObject("statusCode", e.getStatusCode());
//
//        return modelAndView;
//    }
}
