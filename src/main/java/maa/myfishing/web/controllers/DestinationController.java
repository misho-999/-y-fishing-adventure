package maa.myfishing.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/destinations")
public class DestinationController extends BaseController {
//    @GetMapping("/")
//    public ModelAndView getDestination(ModelAndView modelAndView) {
//
//        modelAndView.setViewName("destination/all-destinations.html");
//        return modelAndView;
//    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {
        return super.view("destination/all-destinations.html");
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView) {
        return super.view("destination/add-destination.html");
    }

    @GetMapping("/upcoming")
    public ModelAndView upcoming(ModelAndView modelAndView) {
        return super.view("destination/upcoming-destination.html");
    }

    @GetMapping("/details")
    public ModelAndView details(ModelAndView modelAndView) {
        return super.view("destination/details.html");
    }



}
