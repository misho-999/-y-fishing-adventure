//package maa.myfishing.web.controllers;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends BaseController {
//
//    @ExceptionHandler({Throwable.class})
//    public ModelAndView handleSqlException(Throwable e) {
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject(e.getMessage());
//
//        return modelAndView;
//    }
//}
