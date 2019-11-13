package maa.myfishing.web.controllers;


import maa.myfishing.service.serices.UserService;
import maa.myfishing.validation.user.UserRegisterValidator;
import maa.myfishing.web.annotations.PageTitle;
import maa.myfishing.web.models.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

//
//    private final UserService userService;
//    private final ModelMapper modelMapper;
//    private final UserRegisterValidator userRegisterValidator;


//    @Autowired
//    public UserController(UserService userService, ModelMapper modelMapper, UserRegisterValidator userRegisterValidator) {
//        this.userService = userService;
//        this.modelMapper = modelMapper;
//        this.userRegisterValidator = userRegisterValidator;
//    }
//    private final UserEditValidator userEditValidator;




    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {

        return super.view("user/register", modelAndView);
    }

    @PostMapping("/register")
//    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterModel model, BindingResult bindingResult) {

//        this.userRegisterValidator.validate(model, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);

            return super.view("user/register.html", modelAndView);
        }

//        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
//        this.userService.registerUser(userServiceModel);

        return super.redirect("/login.html");
    }


    @GetMapping("/login")
//    @PreAuthorize("isAnonymous()")
    @PageTitle("Login")
    public ModelAndView login() {
        return super.view("user/login.html");
    }


}
