package maa.myfishing.web.controllers;

import maa.myfishing.service.models.LureServiceModel;
import maa.myfishing.service.serices.LureService;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.FishAllModel;
import maa.myfishing.web.models.FishCreateModel;
import maa.myfishing.web.models.LureAllModel;
import maa.myfishing.web.models.LureCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/lures")
public class LureController extends BaseController {
    private final LureService lureService;
    private final ModelMapper modelMapper;

    @Autowired
    public LureController(LureService lureService, ModelMapper modelMapper) {
        this.lureService = lureService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create/{fishingId}/{townName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Lure")
    public ModelAndView createLure(@PathVariable String fishingId, @PathVariable String townName, ModelAndView modelAndView
            , @ModelAttribute(name = "lureModel") LureCreateModel lureCreateModel) {
        modelAndView.addObject("fishingId", fishingId);
        modelAndView.addObject("townName", townName);

        return super.view("lure/create-lure.html", modelAndView);
    }

    @ModelAttribute(value = "lureModel")
    public LureCreateModel lureCreateModel() {
        return new LureCreateModel();
    }

    @PostMapping("/create/{fishingId}/{townName}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView fishCreateConfirm(@Valid @PathVariable String fishingId, @PathVariable String townName, ModelAndView modelAndView, @ModelAttribute(name = "lureModel")
            LureCreateModel lureCreateModel) {

        LureServiceModel lureServiceModel = this.modelMapper.map(lureCreateModel, LureServiceModel.class);

        this.lureService.createLure(lureServiceModel, fishingId);

//        return super.redirect("/lures/all-for-fishing/" + fishingId);
        return super.redirect("/fishings/all-my-for-destination/" + townName);
    }

    @GetMapping("/all-for-fishing/{fishingId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("All Lure For Destination")
    public ModelAndView allLureForDestination(@PathVariable String fishingId, ModelAndView modelAndView) {
        modelAndView.addObject("lures", this.lureService.getAllLuresByFishingId(fishingId)
                .stream()
                .map(l -> this.modelMapper.map(l, LureAllModel.class))
                .collect(Collectors.toList()));

        return super.view("lure/all-for-fishing-lure.html", modelAndView);
    }
}
