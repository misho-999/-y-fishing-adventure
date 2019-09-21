package myfish.web.servlets;


import myfish.domain.modules.binding.FishRegisterBindingModel;
import myfish.domain.modules.service.FishServiceModel;
import myfish.service.FishService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class FishRegisterServlet extends HttpServlet {

    private final FishService fishService;
    private final ModelMapper modelMapper;

    @Inject
    public FishRegisterServlet(FishService fishService, ModelMapper modelMapper) {
        this.fishService = fishService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register-fish.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FishRegisterBindingModel fishRegisterBindingModel = (FishRegisterBindingModel) req.getAttribute("fishRegisterBindingModel");

        this.fishService.registerFish(this.modelMapper.map(fishRegisterBindingModel, FishServiceModel.class));

        resp.sendRedirect("/");
    }
}
