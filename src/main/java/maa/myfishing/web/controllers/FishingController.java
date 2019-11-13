package maa.myfishing.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fishings")
public class FishingController extends BaseController {

    @GetMapping("/add")
    public ModelAndView addFishing(ModelAndView modelAndView) {
        return super.view("fishing/add-fishing.html");
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
