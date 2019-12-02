package maa.myfishing.web.controllers;

import maa.myfishing.service.models.LureServiceModel;
import maa.myfishing.service.serices.LureService;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.FishCreateModel;
import maa.myfishing.web.models.LureCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @GetMapping("/create/{fishingId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Lure")
    public ModelAndView createLure(@PathVariable String fishingId, ModelAndView modelAndView
            , @ModelAttribute(name = "lureModel") LureCreateModel lureCreateModel) {
        modelAndView.addObject("fishingId", fishingId);
        return super.view("lure/create-lure.html", modelAndView);
    }

    @ModelAttribute(value = "lureModel")
    public LureCreateModel lureCreateModel() {
        return new LureCreateModel();
    }

    @PostMapping("/create/{fishingId}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView fishCreateConfirm(@Valid @PathVariable String fishingId,ModelAndView modelAndView, @ModelAttribute(name = "lureModel")
            LureCreateModel lureCreateModel) {

        LureServiceModel lureServiceModel = this.modelMapper.map(lureCreateModel, LureServiceModel.class);

        this.lureService.createLure(lureServiceModel, fishingId);

        return super.redirect("/home");
    }
}
