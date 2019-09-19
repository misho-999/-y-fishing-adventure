package myfish.web.filters;

import myfish.domain.modules.binding.FishRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/register")
public class FishRegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().toLowerCase().equals("post")) {
            FishRegisterBindingModel fishRegisterBindingModel = new FishRegisterBindingModel();

            fishRegisterBindingModel.setFishName(req.getParameter("fishName"));
            fishRegisterBindingModel.setFirshFamily(req.getParameter("fishFamily"));
            fishRegisterBindingModel.setWeigthInKilograms(Double.parseDouble(req.getParameter("weigthInKilograms")));
            fishRegisterBindingModel.setLengthInSentimeters(Integer.parseInt(req.getParameter("lengthInSentimeters")));
            fishRegisterBindingModel.setDam(req.getParameter("dam"));

            req.setAttribute("fishRegisterBindingModel", fishRegisterBindingModel);
        }

        chain.doFilter(req, resp);
    }
}
