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
    public ModelAndView all (ModelAndView modelAndView) {

        modelAndView.setViewName("destination/all-destination.html");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView) {

        modelAndView.setViewName("destination/add-destination.html");
        return modelAndView;
    }

    @GetMapping("/upcoming")
    public ModelAndView upcoming(ModelAndView modelAndView) {

        modelAndView.setViewName("destination/upcoming-destination.html");
        return modelAndView;
    }

    @GetMapping("/details")
    public ModelAndView details(ModelAndView modelAndView) {

        modelAndView.setViewName("destination/details.html");
        return modelAndView;
    }

    @GetMapping("/add-fishing")
    public ModelAndView addFishing(ModelAndView modelAndView) {

        modelAndView.setViewName("add-fishing.html");
        return modelAndView;
    }



}
