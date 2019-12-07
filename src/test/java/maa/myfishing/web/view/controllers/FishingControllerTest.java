package maa.myfishing.web.view.controllers;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.service.serices.CloudinaryService;
import maa.myfishing.service.serices.FishingService;
import maa.myfishing.web.base.ViewTestBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser(username = "spring")
class FishingControllerTest extends ViewTestBase {
    public final static String TOWN_NAME = "Chushkovo";
    public final static String USERNAME = "spring";
    public final static String CREATE_VIEW_NAME = "fishing/create-fishing.html";
    public final static String ALL_VIEW_NAME = "fishing/all-fishing.html";
    public final static String ALL_FOR_DESTINATION_VIEW_NAME = "fishing/all-for-destination-fishings.html";
    public final static String ALL_MY_FOR_DESTINATION_VIEW_NAME = "fishing/all-my-for-destination-fishings.html";
    public final static String MY_VIEW_NAME = "fishing/my-fishing.html";
    public final static String DELETE_VIEW_NAME = "fishing/delete-fishing.html";

    List<Fishing> fishings = new ArrayList<>();

    @MockBean
    FishingRepository fishingRepository;

    @Autowired
    FishingService fishingService;

    @Autowired
    CloudinaryService cloudinaryService;

    @Test
    void createFishing_WhenDestinationIsPresent_ShouldReturnCreateFishingViewWithStatus200() throws Exception {
        mockMvc.perform(get("/fishings/create/" + FishingControllerTest.TOWN_NAME))
                .andExpect(status().isOk())
                .andExpect(view().name(FishingControllerTest.CREATE_VIEW_NAME));
    }

    @Test
    void createFishingConfirm() throws Exception {
        String description = "Test description";
        LocalDate date = LocalDate.of(2019, 10, 11);
        Fishing fishing = new Fishing();
        fishing.setDate(date);

        Mockito.when(fishingRepository.findByDateAndDescription(date, description)).thenReturn(Optional.of(fishing));

        mockMvc.perform(post("/fishings/create/" + FishingControllerTest.TOWN_NAME))
                .andExpect(status().isOk())
                .andExpect(view().name("/fishings/all-my-for-destination/" + FishingControllerTest.TOWN_NAME));
    }
    //    @PostMapping("/create/{townName}")
    //    @PreAuthorize("hasRole('ROLE_USER')")
    //    public ModelAndView destinationAddToFishingConfirm(@PathVariable String townName, ModelAndView modelAndView, @ModelAttribute(name = "fishingAddModel")
    //        FishingCreateModel fishingCreateModel) throws IOException {
    //
    //        FishingServiceModel fishingServiceModel = this.modelMapper.map(fishingCreateModel, FishingServiceModel.class);
    //
    //        fishingServiceModel.setImgUrl(this.cloudinaryService.uploadImage(fishingCreateModel.getImage()));
    //
    //        this.fishingService.addFishingToDestination(fishingServiceModel, townName);
    //
    //        return super.redirect("/fishings/all-my-for-destination/" + townName);
    //    }

    @Test
    void getAll_WhenFishingsIsPresent_ShouldReturnFishingAllViewWithStatus200() throws Exception {
        Mockito.when(fishingRepository.findAll()).thenReturn(fishings);

        mockMvc.perform(get("/fishings/all"))
                .andExpect(status().isOk())
                .andExpect(view().name(FishingControllerTest.ALL_VIEW_NAME));
    }

    @Test
    void allForDestination_WhenFishingsIsPresent_ShouldReturnFishingAllForDestinationViewWithStatus200() throws Exception {
        Mockito.when(fishingRepository.findAllFishingByTownName(FishingControllerTest.TOWN_NAME)).thenReturn(fishings);

        mockMvc.perform(get("/fishings/all-for-destination/" + FishingControllerTest.TOWN_NAME))
                .andExpect(status().isOk())
                .andExpect(view().name(FishingControllerTest.ALL_FOR_DESTINATION_VIEW_NAME));
    }

    @Test
    void allMyForDestination() throws Exception {
        Mockito.when(fishingRepository
                .findAllFishingsByUsernameAndTownName(FishingControllerTest.USERNAME, FishingControllerTest.TOWN_NAME))
                .thenReturn(fishings);

        mockMvc.perform(get("/fishings/all-my-for-destination/" + FishingControllerTest.TOWN_NAME))
                .andExpect(status().isOk())
                .andExpect(view().name(FishingControllerTest.ALL_MY_FOR_DESTINATION_VIEW_NAME));
    }

    @Test
    void allMyFishings() throws Exception {
        Mockito.when(fishingRepository
                .findAllFishingByUsername(FishingControllerTest.USERNAME))
                .thenReturn(fishings);

        mockMvc.perform(get("/fishings/my"))
                .andExpect(status().isOk())
                .andExpect(view().name(FishingControllerTest.MY_VIEW_NAME));
    }

    @Test
    void deleteProduct() throws Exception {
        String id = "1";
        Fishing fishing = new Fishing();
        Mockito.when(fishingRepository.findById(id)).thenReturn(Optional.of(fishing));

        mockMvc.perform(get("/fishings/delete/"+id+"/"+FishingControllerTest.TOWN_NAME))
                .andExpect(status().isOk())
                .andExpect(view().name(FishingControllerTest.DELETE_VIEW_NAME));
    }

    @Test
    void deleteFishingConfirm() {
    }

    private List<Fishing> getFishings() {
        Destination destination1 = new Destination();
        Destination destination2 = new Destination();
        destination1.setTownName("Chushkovo1");
        destination2.setTownName("Chushkovo2");

        Fishing fishing1 = new Fishing();
        Fishing fishing2 = new Fishing();
        fishing1.setDestination(destination1);
        fishing2.setDestination(destination2);

        fishings.add(fishing1);
        fishings.add(fishing2);

        return fishings;
    }
}