package maa.myfishing.web.controllers;


import maa.myfishing.service.models.FishServiceModel;
import maa.myfishing.service.serices.FishService;
import maa.myfishing.validation.fish.FishCreateValidator;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.FishCreateModel;
import maa.myfishing.web.models.FishAllViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/fishes")
public class FishController extends BaseController {
    private final FishService fishService;
    private final FishCreateValidator fishCreateValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public FishController(FishService fishService, FishCreateValidator fishCreateValidator, ModelMapper modelMapper) {
        this.fishService = fishService;
        this.fishCreateValidator = fishCreateValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create/{fishingId}/{townName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Fish")
    public ModelAndView createFish(@PathVariable String fishingId, @PathVariable String townName, ModelAndView modelAndView
            , @ModelAttribute(name = "fishModel") FishCreateModel fishCreateModel) {
        modelAndView.addObject("fishingId", fishingId);
        modelAndView.addObject("townName", townName);

        return super.view("fish/create-fish.html", modelAndView);
    }

    @ModelAttribute(value = "fishModel")
    public FishCreateModel fishAddModel() {
        return new FishCreateModel();
    }

    @PostMapping("/create/{fishingId}/{townName}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createFishConfirm(@Valid @PathVariable String fishingId, @PathVariable String townName, ModelAndView modelAndView
            , @ModelAttribute(name = "fishModel") FishCreateModel fishCreateModel, BindingResult bindingResult) {

        this.fishCreateValidator.validate(fishCreateModel, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("fishModel", fishCreateModel);
            modelAndView.addObject("fishingId", fishingId);

            return super.view("fish/create-fish.html", modelAndView);
        }

        FishServiceModel fishServiceModel = this.modelMapper.map(fishCreateModel, FishServiceModel.class);

        this.fishService.createFish(fishServiceModel, fishingId);

        return super.redirect("/fishings/all-my-for-destination/" + townName);

    }

    @GetMapping("/all-for-fishing/{fishingId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("All Fish For Destination")
    public ModelAndView allFishForDestination(@PathVariable String fishingId, ModelAndView modelAndView) {
        modelAndView.addObject("fishes", this.fishService.getAllFishesByFishingId(fishingId)
                .stream()
                .map(f -> this.modelMapper.map(f, FishAllViewModel.class))
                .collect(Collectors.toList()));
        modelAndView.addObject("fishingId", fishingId);

        return super.view("fish/all-for-fishing-fish.html", modelAndView);
    }

    @PostMapping("/delete/{id}/{fishingId}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView deleteFishConfirm(@PathVariable String id, @PathVariable String fishingId) {
        this.fishService.deleteFish(id);

        return super.redirect("/fishes/all-for-fishing/" + fishingId);
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
