package maa.myfishing.web.view.controllers.fishing;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.service.serices.CloudinaryService;
import maa.myfishing.service.serices.FishingService;
import maa.myfishing.web.base.ViewBaseTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser(username = "spring")
class FishingControllerTest extends FishingControllerBaseTest {
    public final static String TOWN_NAME = "Chushkovo";
    public final static String USERNAME = "spring";
    public final static String CREATE_VIEW_NAME = "fishing/create-fishing.html";
    public final static String ALL_VIEW_NAME = "fishing/all-fishing.html";
    public final static String ALL_FOR_DESTINATION_VIEW_NAME = "fishing/all-for-destination-fishings.html";
    public final static String ALL_MY_FOR_DESTINATION_VIEW_NAME = "fishing/all-my-for-destination-fishings.html";
    public final static String MY_VIEW_NAME = "fishing/my-fishing.html";
    public final static String DELETE_VIEW_NAME = "fishing/delete-fishing.html";

    @Test
    void createFishing_WhenDestinationIsPresent_ShouldReturnCreateFishingViewWithStatus200() throws Exception {
        mockMvc.perform(get("/fishings/create/" + FishingControllerTest.TOWN_NAME))
                .andExpect(status().isOk())
                .andExpect(view().name(FishingControllerTest.CREATE_VIEW_NAME));
    }

//    @Test
//    void createFishingConfirm() throws Exception {
//        String townName = "Test Town";
//        String description = "Test description";
//        Destination destination = new Destination();
//        destination.setTownName(townName);
//
//        LocalDate date = LocalDate.of(2019, 10, 11);
//        Fishing fishing = new Fishing();
//        fishing.setDate(date);
//
//        List<Fishing> mockFishings = getMockFishings();
//
//        Mockito.when(fishingRepository.findByDateAndDescription(date, description)).thenReturn(Optional.of(fishing));
//        Mockito.when(fishingRepository.findAllFishingByTownName(destination.getTownName())).thenReturn(mockFishings);
//        Mockito.when(fishingRepository.saveAndFlush(any(Fishing.class))).thenReturn(fishing);
//        Mockito.when(destinationRepository.findByTownName(townName)).thenReturn(Optional.of(destination));
//        Mockito.when(destinationRepository.save(any(Destination.class))).thenReturn(destination);
//
//        mockMvc.perform(post("/fishings/create/" + FishingControllerTest.TOWN_NAME))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/fishings/all-my-for-destination/" + FishingControllerTest.TOWN_NAME));
//    }
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
    void deleteFishingConfirm() throws Exception {
        String id = "1";
        Fishing fishing = getMockFishing();
        Destination mockDestination = getMockDestination();

        Mockito.when(fishingRepository.findById(id)).thenReturn(Optional.of(fishing));
        Mockito.when(destinationRepository.findByTownName(FishingControllerTest.TOWN_NAME)).thenReturn(Optional.of(mockDestination));
        Mockito.when(destinationRepository.save(any(Destination.class))).thenReturn(mockDestination);


        mockMvc.perform(post("/fishings/delete/" + id + "/" + FishingControllerTest.TOWN_NAME))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/fishings/all-my-for-destination/" + FishingControllerTest.TOWN_NAME));

        Mockito.verify(fishingRepository, times(1)).delete(fishing);
    }

}