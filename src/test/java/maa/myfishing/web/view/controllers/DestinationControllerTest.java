package maa.myfishing.web.view.controllers;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.UserInfo;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.UserInfoRepository;
import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.serices.DestinationService;
import maa.myfishing.service.serices.UserInfoService;
import maa.myfishing.validation.destination.DestinationCreateValidator;
import maa.myfishing.web.base.ViewTestBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username = "spring")
class DestinationControllerTest extends ViewTestBase {
    public final static String DESTINATION_CREATE_VIEW_NAME = "destination/create-destination.html";
    public final static String DESTINATION_DETAILS_VIEW_NAME = "destination/details-destination.html";
    public final static String DESTINATION_ALL_VIEW_NAME = "destination/all-destinations.html";
    public final static String DESTINATION_MY_VIEW_NAME = "destination/my-destinations.html";
    public final static String DESTINATION_EDIT_VIEW_NAME = "destination/edit-destination.html";
    public final static String DESTINATION_DELETE_VIEW_NAME = "destination/delete-destination.html";

    List<Destination> destinations = new ArrayList<>();

    @MockBean
    DestinationRepository destinationRepository;

    @MockBean
    UserInfoRepository userInfoRepository;

    @MockBean
    DestinationCreateValidator destinationCreateValidator;

    @MockBean
    BindingResult bindingResult;

    @Autowired
    DestinationService destinationService;

    @Autowired
    UserInfoService userInfoService;

    @Test
    void create() {

    }

    @Test
    void destinationCreateConfirm_whenDestination_shouldReturnHeroDetailsViewWith200() throws Exception {
        String username = "spring";
        String townName = "Chushkovo";
        DestinationServiceModel destinationServiceModel = new DestinationServiceModel();
        destinationServiceModel.setTownName(townName);
        destinationServiceModel.setPopulation(1500);
        destinationServiceModel.setAltitude(560);
        destinationServiceModel.setDescription("RRRRRR");

        Destination destination = new Destination();
        destination.setTownName("Test");

        UserInfo userInfo = new UserInfo();

        userInfo.setDestinations(getDestinations());

        Mockito.when(destinationRepository.saveAndFlush(any(Destination.class))).thenReturn(destination);
        Mockito.when(destinationRepository.findByTownName(townName)).thenReturn(Optional.of(destination));
        Mockito.when(userInfoRepository.findByUserUsername(username)).thenReturn(Optional.of(userInfo));
        Mockito.when(userInfoRepository.saveAndFlush(any(UserInfo.class))).thenReturn(userInfo);

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        destinationService.createDestination(destinationServiceModel);

        this.userInfoService.addDestinationToMyDestinations(destinationServiceModel.getTownName(), username);

        mockMvc.perform(post("/destinations/create"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/destinations/my"));
    }

    @Test
    void allDestinations_WhenDestinationsIsPresent_ShouldReturnMyDestinationsViewWithStatus200() throws Exception {
        List<Destination> destinations = getDestinations();
        Mockito.when(destinationRepository.findAllByOrderByFishingsCountDesc()).thenReturn(destinations);

        mockMvc.perform(get("/destinations/all"))
                .andExpect(status().isOk())
                .andExpect(view().name(DestinationControllerTest.DESTINATION_ALL_VIEW_NAME));
    }

    @Test
    void myDestinations_WhenDestinationsIsPresent_ShouldReturnMyDestinationsViewWithStatus200() throws Exception {
        String username = "Pencho";
        List<Destination> destinations = getDestinations();
        Mockito.when(destinationRepository.findDestinationsByUsername(username)).thenReturn(destinations);

        mockMvc.perform(get("/destinations/my"))
                .andExpect(status().isOk())
                .andExpect(view().name(DestinationControllerTest.DESTINATION_MY_VIEW_NAME));
    }

    @Test
    void addToMyDestinationsConfirm_WhenDestinationsIsPresent_ShouldRedirectWithStatus302() throws Exception {
        String townName = "Cushkovo";
        String username = "spring";

        UserInfo userInfo = new UserInfo();
        Destination destination = new Destination();
        destinations.add(destination);
        destination.setTownName("Cushkovo");

        Mockito.when(userInfoRepository.findByUserUsername(username)).thenReturn(Optional.of(userInfo));
        Mockito.when(destinationRepository.findByTownName(townName)).thenReturn(Optional.of(destination));

        mockMvc.perform(post("/destinations/add-to-my/" + townName))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/destinations/all"));
    }

    @Test
    void addToMyDestinationsConfirm_WhenTownNameIsNotPresent_shouldReturnErrorViewWith404() throws Exception {
        String townName = "Cushkovo";
        String username = "spring";

        userInfoService.addDestinationToMyDestinations(townName, username);

        mockMvc.perform(post("/destinations/add-to-my/" + townName))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"));
    }

    @Test
    void destinationDetails_WhenDestinationsIsPresent_ShouldReturnDestinationDetailsViewWithStatus200() throws Exception {
        Destination destination = getMockDestination();

        Mockito.when(destinationRepository.findById(destination.getId())).thenReturn(Optional.of(destination));

        mockMvc.perform(get("/destinations/details/" + destination.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name(DestinationControllerTest.DESTINATION_DETAILS_VIEW_NAME));
    }

    @Test
    void destinationDetails_WhenIdIsNotPresent_shouldReturnErrorViewWithStatus200() throws Exception {
        String id = "1";

        mockMvc.perform(get("/destinations/details/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("error.html"));
    }

    @Test
    void editDestination_WhenDestinationsIsPresent_ShouldReturnDestinationEditViewWithStatus200() throws Exception {
        Destination destination = getMockDestination();

        Mockito.when(destinationRepository.findById(destination.getId())).thenReturn(Optional.of(destination));

        mockMvc.perform(get("/destinations/edit/" + destination.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name(DestinationControllerTest.DESTINATION_EDIT_VIEW_NAME));
    }

    @Test
    void editDestination_WhenIdIsNotPresent_shouldReturnErrorViewWithStatus200() throws Exception {
        String destinationId = "1";

        mockMvc.perform(get("/destinations/edit/" + destinationId))
                .andExpect(status().isOk())
                .andExpect(view().name("error.html"));
    }

    @Test
    void editDestinationConfirm() throws Exception {
        Destination destination = getMockDestination();

        Mockito.when(destinationRepository.findById(destination.getId())).thenReturn(Optional.of(destination));

        mockMvc.perform(post("/destinations/edit/" + destination.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name(DestinationControllerTest.DESTINATION_MY_VIEW_NAME));
    }

    @Test
    void deleteDestination() throws Exception {
        Destination destination = getMockDestination();

        Mockito.when(destinationRepository.findById(destination.getId())).thenReturn(Optional.of(destination));

        mockMvc.perform(get("/destinations/delete/" + destination.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name(DestinationControllerTest.DESTINATION_DELETE_VIEW_NAME));
    }

    @Test
    void deleteDestinationConfirm() throws Exception {
        Destination destination = getMockDestination();

        Mockito.when(destinationRepository.findById(destination.getId())).thenReturn(Optional.of(destination));

        mockMvc.perform(post("/destinations/delete/" + destination.getId()))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/destinations/my"));
    }

    private List<Destination> getDestinations() {
        List<Destination> destinations = new ArrayList<>();
        Destination destination1 = new Destination();
        Destination destination2 = new Destination();
        Destination destination3 = new Destination();
        destination1.setTownName("Chushkovo1");
        destination2.setTownName("Chushkovo2");
        destination3.setTownName("Chushkovo3");

        destinations.add(destination1);
        destinations.add(destination2);
        destinations.add(destination3);

        return destinations;
    }

    private Destination getMockDestination() {
        String destinationId = "1";
        Destination destination = new Destination();
        destination.setTownName("Chushkovo");
        destination.setId("1");

        return destination;
    }
}