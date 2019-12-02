package maa.myfishing.web.controllers;


import maa.myfishing.service.models.FishServiceModel;
import maa.myfishing.service.serices.FishService;
import maa.myfishing.validation.fish.FishCreateValidator;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.FishCreateModel;
import maa.myfishing.web.models.FishAllModel;
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

    @GetMapping("/create/{fishingId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Fish")
    public ModelAndView createFish(@PathVariable String fishingId, ModelAndView modelAndView
            , @ModelAttribute(name = "fishModel") FishCreateModel fishCreateModel) {
        modelAndView.addObject("fishingId", fishingId);

        return super.view("fish/fish-create.html", modelAndView);
    }

    @ModelAttribute(value = "fishModel")
    public FishCreateModel fishAddModel() {
        return new FishCreateModel();
    }

    @PostMapping("/create/{fishingId}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createFishConfirm(@Valid @PathVariable String fishingId, ModelAndView modelAndView, @ModelAttribute(name = "fishModel")
            FishCreateModel fishCreateModel, BindingResult bindingResult) {

        this.fishCreateValidator.validate(fishCreateModel, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("fishModel", fishCreateModel);
            modelAndView.addObject("fishingId", fishingId);

            return super.view("fish/fish-create.html", modelAndView);
        }

        FishServiceModel fishServiceModel = this.modelMapper.map(fishCreateModel, FishServiceModel.class);

        this.fishService.createFish(fishServiceModel, fishingId);

        return super.redirect("/fishes/all-for-fishing/" + fishingId);
    }

    @GetMapping("/all-for-fishing/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("All Fish For Destination")
    public ModelAndView allForDestination(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("fishes", this.fishService.getAllFishesByFishingId(id)
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
