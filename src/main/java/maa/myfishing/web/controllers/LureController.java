package maa.myfishing.web.controllers;

import maa.myfishing.data.models.Lure;
import maa.myfishing.service.models.FishServiceModel;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.FishCreateModel;
import maa.myfishing.web.models.LureCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/lures")
public class LureController extends BaseController {

    @Autowired
    public LureController() {
    }

    @GetMapping("/create/{fishingId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Lure")
    public ModelAndView createLure(@PathVariable String fishingId, ModelAndView modelAndView
            , @ModelAttribute(name = "lureModel") LureCreateModel lureCreateModel) {
        return super.view("lure/lure-create.html", modelAndView);
    }

    @ModelAttribute(value = "lureModel")
    public LureCreateModel lureCreateModel(){
        return new LureCreateModel();
    }

//    @PostMapping("/create")
//    @PreAuthorize("isAuthenticated()")
//    public ModelAndView fishCreateConfirm(@Valid ModelAndView modelAndView, @ModelAttribute(name = "lureModel")
//            FishCreateModel fishCreateModel, BindingResult bindingResult) {
//
//        this.fishCreateValidator.validate(fishCreateModel, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            if (fishCreateModel.getFishingId().equals("")) {
//                fishCreateModel.setFishingId(fishCreateModel.getFishingUrl().replace("http://localhost:8000/fish/create/", ""));
//            }
//
//            modelAndView.addObject("fishModel", fishCreateModel);
//
//            return super.view("fish/fish-create.html", modelAndView);
//        }
//
//        FishServiceModel fishServiceModel = this.modelMapper.map(fishCreateModel, FishServiceModel.class);
//
//        this.fishService.createFish(fishServiceModel);
//
//        return super.redirect("/fish/all-for-fishing/" + fishServiceModel.getFishingId());
//    }
}
