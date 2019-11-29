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
@RequestMapping("/fish")
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

    @GetMapping("/create/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Fish")
    public ModelAndView addFishing(@PathVariable String id, ModelAndView modelAndView, @ModelAttribute(name = "fishModel") FishCreateModel fishCreateModel) {
        return super.view("fish/add-caught-fish.html", modelAndView);
    }

    @ModelAttribute(value = "fishModel")
    public FishCreateModel fishAddModel() {
        return new FishCreateModel();
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView fishCreateConfirm(@Valid ModelAndView modelAndView, @ModelAttribute(name = "fishModel")
            FishCreateModel fishCreateModel, BindingResult bindingResult) {

        this.fishCreateValidator.validate(fishCreateModel, bindingResult);

        if (bindingResult.hasErrors()) {
            if (fishCreateModel.getFishingId().equals("")) {
                fishCreateModel.setFishingId(fishCreateModel.getFishingUrl().replace("http://localhost:8000/fish/create/", ""));
            }

            modelAndView.addObject("fishModel", fishCreateModel);

            return super.view("fish/add-caught-fish.html", modelAndView);
        }

        FishServiceModel fishServiceModel = this.modelMapper.map(fishCreateModel, FishServiceModel.class);

        this.fishService.createFish(fishServiceModel);

        return super.redirect("/fishings/all");
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
