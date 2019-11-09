package maa.myfishing.web.controllers;


import maa.myfishing.service.models.OverNightBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {


//    @GetMapping("/")
//    public ModelAndView getIndex(ModelAndView modelAndView) {
//
//        modelAndView.setViewName("index.html");
//        return modelAndView;
//    }

    @GetMapping("/register")
    public ModelAndView getRegister(ModelAndView modelAndView) {

        modelAndView.setViewName("register.html");
//        return modelAndView;

        return super.view("user/register", modelAndView);
    }

    @GetMapping("/login")
    public ModelAndView getLogin(ModelAndView modelAndView) {

        modelAndView.setViewName("user/login.html");
        return modelAndView;
    }


//    @GetMapping("/home")
//    public ModelAndView getHome(ModelAndView modelAndView) {
//
//        modelAndView.setViewName("home.html");
//        return modelAndView;
//    }



    @GetMapping("/add-fishing")
    public ModelAndView getAddFishing(ModelAndView modelAndView, @ModelAttribute(name = "overnight") OverNightBindingModel overnight) {
        modelAndView.addObject("model", overnight);
        modelAndView.setViewName("add-fishing.html");
        return modelAndView;

    }
}
