package maa.myfishing.web.controllers;


import maa.myfishing.service.models.OvertightBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/login")
    public ModelAndView getLogin(ModelAndView modelAndView) {

        modelAndView.setViewName("Login.html");
        return modelAndView;
    }


    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView modelAndView) {

        modelAndView.setViewName("home.html");
        return modelAndView;
    }

    @GetMapping("/destination")
    public ModelAndView getDestination(ModelAndView modelAndView) {

        modelAndView.setViewName("destination.html");
        return modelAndView;
    }
    //add-fishing

    @GetMapping("/add-fishing")
    public ModelAndView getAddFishing(ModelAndView modelAndView, @ModelAttribute(name = "overnight") OvertightBindingModel overnight) {
        modelAndView.addObject("model", overnight);
        modelAndView.setViewName("add-fishing.html");
        return modelAndView;
    }

//    @GetMapping("/register")
//    public ModelAndView register(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model) {
//        modelAndView.addObject("model", model);
//
//        return super.view("user/register", modelAndView);
//    }
}
