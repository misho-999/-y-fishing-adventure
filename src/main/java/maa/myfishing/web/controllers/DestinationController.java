package maa.myfishing.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/destinations")
public class DestinationController {
    @GetMapping("/")
    public ModelAndView getDestination(ModelAndView modelAndView) {

        modelAndView.setViewName("destination/destination.html");
        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getAllDestination(ModelAndView modelAndView) {

        modelAndView.setViewName("destination/all-destination.html");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView getADDDestination(ModelAndView modelAndView) {

        modelAndView.setViewName("destination/add-destination.html");
        return modelAndView;
    }


}
