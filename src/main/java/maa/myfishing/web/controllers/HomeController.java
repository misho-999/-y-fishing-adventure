package maa.myfishing.web.controllers;

import maa.myfishing.service.serices.DestinationService;
import maa.myfishing.web.annotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private final DestinationService destinationService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(DestinationService destinationService, ModelMapper modelMapper) {
        this.destinationService = destinationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
//    @PreAuthorize("isAnonymous()")
    @PageTitle("Index")
    public ModelAndView index() {
        return super.view("index");
    }

    @GetMapping("/home")
//    @PreAuthorize("isAuthenticated()")
    @PageTitle("Home")
    public ModelAndView home(ModelAndView modelAndView) {
//        List<CategoryViewModel> categories = this.categoryService.findAllCategories()
//                .stream()
//                .map(category -> this.modelMapper.map(category, CategoryViewModel.class))
//                .collect(Collectors.toList());

//        modelAndView.setViewName("home.html");
//        return modelAndView;

        return super.view("home", modelAndView);
    }
}
