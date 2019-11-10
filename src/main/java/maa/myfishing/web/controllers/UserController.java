package maa.myfishing.web.controllers;


import maa.myfishing.service.models.OverNightBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {

        modelAndView.setViewName("register.html");
        return super.view("user/register", modelAndView);
    }

//    @PostMapping("/register")
////    @PreAuthorize("isAnonymous()")
//    public ModelAndView registerConfirm(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model, BindingResult bindingResult) {
//        this.userRegisterValidator.validate(model, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            model.setPassword(null);
//            model.setConfirmPassword(null);
//            modelAndView.addObject("model", model);
//
//            return super.view("user/register", modelAndView);
//        }
//
//        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
//        this.userService.registerUser(userServiceModel);
//
//        return super.redirect("/login");
//    }



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
