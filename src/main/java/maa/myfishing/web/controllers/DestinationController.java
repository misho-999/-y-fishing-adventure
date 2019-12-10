package maa.myfishing.web.controllers;

import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.eroors.TownAlreadyExistException;
import maa.myfishing.eroors.UserNotFoundException;
import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.serices.DestinationService;
import maa.myfishing.service.serices.UserInfoService;
import maa.myfishing.validation.destination.DestinationCreateValidator;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.destination.DestinationCreateModel;
import maa.myfishing.web.models.destination.DestinationAllViewModel;
import maa.myfishing.web.models.destination.DestinationDetailsViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/destinations")
public class DestinationController extends BaseController {

    private final UserInfoService userInfoService;
    private final DestinationService destinationService;
    private final DestinationCreateValidator destinationCreateValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public DestinationController(UserInfoService userInfoService, DestinationService destinationService, DestinationCreateValidator destinationCreateValidator, ModelMapper modelMapper) {
        this.userInfoService = userInfoService;
        this.destinationService = destinationService;
        this.destinationCreateValidator = destinationCreateValidator;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Add Destination")
    public ModelAndView create(ModelAndView modelAndView, @ModelAttribute(name = "model") DestinationCreateModel destinationCreateModel) {
        return super.view("destination/create-destination.html");
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView destinationCreateConfirm(@Valid ModelAndView modelAndView, @ModelAttribute(name = "model")
            DestinationCreateModel destinationCreateModel, Principal principal, BindingResult bindingResult) {

        this.destinationCreateValidator.validate(destinationCreateModel, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", destinationCreateModel);

            return super.view("destination/create-destination.html", modelAndView);
        }

        DestinationServiceModel destinationServiceModel = this.modelMapper.map(destinationCreateModel, DestinationServiceModel.class);

        this.destinationService.createDestination(destinationServiceModel);

        this.userInfoService.addDestinationToMyDestinations(destinationServiceModel.getTownName(), principal.getName());

        return super.redirect("/destinations/my");
    }


    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("All Destinations")
    public ModelAndView allDestinations(ModelAndView modelAndView) {
        modelAndView.addObject("destinations", this.destinationService.getAllDestinations()
                .stream()
                .map(p -> this.modelMapper.map(p, DestinationAllViewModel.class))
                .collect(Collectors.toList()));

        return super.view("destination/all-destinations.html", modelAndView); //Няма View
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("My Destinations")
    public ModelAndView myDestinations(ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("destinations", this.destinationService.getMyDestinations(principal.getName())
                .stream()
                .map(p -> this.modelMapper.map(p, DestinationAllViewModel.class))
                .collect(Collectors.toList()));

        return super.view("destination/my-destinations.html", modelAndView);
    }

    @PostMapping("/add-to-my/{townName}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addToMyDestinationsConfirm(@PathVariable String townName
            , @ModelAttribute DestinationCreateModel destinationCreateModel, Principal principal) {

        this.userInfoService.addDestinationToMyDestinations(townName, principal.getName());

        return super.redirect("/destinations/all");
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Destination Details")
    public ModelAndView destinationDetails(@PathVariable String id, ModelAndView modelAndView, Principal principal) {
        DestinationDetailsViewModel destination =
                this.modelMapper.map(this.destinationService.getDestinationById(id), DestinationDetailsViewModel.class);

        modelAndView.addObject("destination", destination);

        return super.view("destination/details-destination.html", modelAndView);
    }


    @GetMapping("/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Edit Destination")
    public ModelAndView editDestination(@PathVariable String id, ModelAndView modelAndView) {
        DestinationServiceModel destinationServiceModel = this.destinationService.getDestinationById(id);
        DestinationCreateModel destinationCreateModel = this.modelMapper.map(destinationServiceModel, DestinationCreateModel.class);

        modelAndView.addObject("destination", destinationServiceModel);
        modelAndView.addObject("destinationId", id);

        return super.view("destination/edit-destination.html", modelAndView);
    }


    @PostMapping("/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editDestinationConfirm(@PathVariable String id, @ModelAttribute DestinationCreateModel destinationCreateModel) {
        this.destinationService.editDestination(id, this.modelMapper.map(destinationCreateModel, DestinationServiceModel.class));

        return super.redirect("/destinations/my");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Delete Destination")
    public ModelAndView deleteDestination(@PathVariable String id, ModelAndView modelAndView) {
        DestinationServiceModel destinationServiceModel = this.destinationService.getDestinationById(id);
        DestinationCreateModel destinationCreateModel = this.modelMapper.map(destinationServiceModel, DestinationCreateModel.class);

        modelAndView.addObject("destination", destinationCreateModel);
        modelAndView.addObject("destinationId", id);

        return super.view("destination/delete-destination.html", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView deleteDestinationConfirm(@PathVariable String id) {
        this.destinationService.deleteDestination(id);

        return super.redirect("/destinations/my");
    }

    @ExceptionHandler({DestinationNotFoundException.class, TownAlreadyExistException.class, UserNotFoundException.class})
    public ModelAndView handleDestinationNotFound(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error.html");
        modelAndView.addObject("message", e.getMessage());
//        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
