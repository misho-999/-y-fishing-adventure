package maa.myfishing.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FaviconInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String link = "http://icons.iconarchive.com/icons/google/noto-emoji-animals-nature/128/22293-fish-icon.png";

        if (modelAndView != null) {
            modelAndView.addObject("favicon", link);
        }
    }
}
