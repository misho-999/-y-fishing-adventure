package maa.myfishing.web.controllers;

import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.FishingAddModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/fishings")
public class FishingController extends BaseController {

    @GetMapping("/add/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Fishing")
    public ModelAndView addFishing(@PathVariable String id, ModelAndView modelAndView) {
        return super.view("fishing/add-fishing.html");
    }

    @ModelAttribute(value = "fishingAddModel")
    public FishingAddModel fishingAddModel() {
        return new FishingAddModel();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView destinationAddConfirm(ModelAndView modelAndView, @ModelAttribute(name = "fishingAddModel")
            FishingAddModel fishingAddModel, Principal principal) {

        Fishing

//        DestinationServiceModel destinationServiceModel = this.modelMapper.map(destinationAddModel, DestinationServiceModel.class);
//
//        this.destinationService.addDestination(destinationServiceModel);
//
//        this.userInfoService.addDestination(destinationServiceModel.getTownName(), principal.getName());

        return super.redirect("/fishings/all");
    }


    @GetMapping("/all")
    public ModelAndView AllFishing(ModelAndView modelAndView) {
        return super.view("fishing/all-fishing.html");
    }

    @GetMapping("/add-caught-fish")
    public ModelAndView AddCaughtFish(ModelAndView modelAndView) {
        return super.view("fishing/add-caught-fish.html");
    }
}
