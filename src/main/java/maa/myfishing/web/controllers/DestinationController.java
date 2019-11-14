package maa.myfishing.web.controllers;

import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.serices.CloudinaryService;
import maa.myfishing.service.serices.DestinationService;
import maa.myfishing.web.models.DestinationAddModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/destinations")
public class DestinationController extends BaseController {

    private final DestinationService destinationService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @Autowired
    public DestinationController(DestinationService destinationService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.destinationService = destinationService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {
        return super.view("destination/destinations-all.html");
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView) {
        return super.view("destination/destination-add.html");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView destinationAddConfirm(@ModelAttribute DestinationAddModel model) {
        DestinationServiceModel productServiceModel = this.modelMapper.map(model, DestinationServiceModel.class);
        System.out.println();
        return super.redirect("/destinations");
    }

    @GetMapping("/details")
    public ModelAndView details(ModelAndView modelAndView) {
        return super.view("destination/details.html");
    }
}
