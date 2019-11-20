package maa.myfishing.web.controllers;

import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.serices.DestinationService;
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
    private final ModelMapper modelMapper;

    @Autowired
    public DestinationController(UserInfoService userInfoService, DestinationService destinationService, ModelMapper modelMapper) {
        this.userInfoService = userInfoService;
        this.destinationService = destinationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PageTitle("All Destinations")
    public ModelAndView all(ModelAndView modelAndView) {
        modelAndView.addObject("destinations", this.destinationService.getAllDestinations()
                .stream()
                .map(p -> this.modelMapper.map(p, DestinationAllModel.class))
                .collect(Collectors.toList()));

        return super.view("destination/destinations-all.html", modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Add Destination")
    public ModelAndView add(ModelAndView modelAndView) {
        return super.view("destination/destination-add.html");
    }

    @ModelAttribute(value = "destinationModel")
    public DestinationAddModel destinationAddModel() {
        return new DestinationAddModel();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView destinationAddConfirm(ModelAndView modelAndView, @ModelAttribute(name = "destinationModel")
            DestinationAddModel destinationAddModel, Principal principal) {

        DestinationServiceModel destinationServiceModel = this.modelMapper.map(destinationAddModel, DestinationServiceModel.class);

        this.destinationService.addDestination(destinationServiceModel);

        this.userInfoService.addDestination(destinationServiceModel.getTownName(), principal.getName());

        return super.redirect("/destinations/all");
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Destination Details")
    public ModelAndView destinationDetails(@PathVariable String id, ModelAndView modelAndView) {
        DestinationDetailsViewModel destination =
                this.modelMapper.map(this.destinationService.getDestinationById(id), DestinationDetailsViewModel.class);

        modelAndView.addObject("destination", destination);

        return super.view("destination/details.html", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Edit Destination")
    public ModelAndView editProduct(@PathVariable String id, ModelAndView modelAndView) {
        DestinationServiceModel destinationServiceModel = this.destinationService.getDestinationById(id);
        DestinationAddModel destinationAddModel = this.modelMapper.map(destinationServiceModel, DestinationAddModel.class);

        modelAndView.addObject("destination", destinationServiceModel);
        modelAndView.addObject("destinationId", id);

        return super.view("destination/destination-edit", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editProductConfirm(@PathVariable String id, @ModelAttribute DestinationAddModel destinationAddModel) {
        this.destinationService.editDestination(id, this.modelMapper.map(destinationAddModel, DestinationServiceModel.class));

        return super.redirect("/destinations/details/" + id);
    }

}
