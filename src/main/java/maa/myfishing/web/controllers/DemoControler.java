package maa.myfishing.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoControler {


    @GetMapping("/")
    public ModelAndView getIndex(ModelAndView modelAndView) {

        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(ModelAndView modelAndView) {

        modelAndView.setViewName("register.html");
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView modelAndView) {

        modelAndView.setViewName("home.html");
        return modelAndView;
    }
    //add-fishing

    @GetMapping("/add-fishing")
    public ModelAndView getAddFishing(ModelAndView modelAndView) {

        modelAndView.setViewName("add-fishing.html");
        return modelAndView;
    }
}
