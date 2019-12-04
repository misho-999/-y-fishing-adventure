package maa.myfishing.service.serices;

import maa.myfishing.base.TestBase;
import maa.myfishing.constants.validation.DestinationValidationConstants;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.data.reposipories.UserInfoRepository;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.service.models.DestinationServiceModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DestinationServiceTest extends TestBase {
    List<Destination> destinations;

    @MockBean
    DestinationRepository destinationRepository;

    @MockBean
    FishingRepository fishingRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    DestinationService destinationService;

    @BeforeEach
    private void setupTest() {
        destinations = new ArrayList<>();
    }

    @Test
    void createDestination() {
//        DestinationServiceModel destinationServiceModel = getDestinationServiceModel();
//
//        destinationService.createDestination(destinationServiceModel);
//
//        ArgumentCaptor<Destination> argument = ArgumentCaptor.forClass(Destination.class);
//        Mockito.verify(destinationRepository).save(argument.capture());
//
//        Destination destination = argument.getValue();
//
//        assertNotNull(destination);
    }

    @Test
    void getDestinationByTownName_WhenTownIsNotPresent_ShouldThrowException() {
        String townName = "Chushkovo";

        Mockito.when(destinationRepository.findByTownName(townName))
                .thenReturn(Optional.empty());

        assertThrows(
                DestinationNotFoundException.class,
                () -> destinationService.getDestinationByTownName(townName));

    }

    @Test
    void getDestinationByTownName_WhenTownIsExist_ShouldDestination() {
        String townName = "Chushkovo";

        Destination destination = new Destination();
        destination.setTownName("Chushkovo");

        Mockito.when(destinationRepository.findByTownName(townName))
                .thenReturn(Optional.of(destination));


        DestinationServiceModel destinationServiceModel = destinationService.getDestinationByTownName(townName);

        assertEquals(destination.getTownName(), destinationServiceModel.getTownName());

    }


    @Test
    void getAllDestinations_WhenDestinationsAreNotPresent_ShouldReturnEmptyList() {
        Mockito.when(destinationRepository.findAll()).thenReturn(destinations);

        assertEquals(0, destinations.size());
    }

    @Test
    void getAllDestinations_WhenDestinationsArePresent_ShouldReturnCorrectResult() {
        Destination dest1 = new Destination();
        Destination dest2 = new Destination();
        Destination dest3 = new Destination();

        destinations.add(dest1);
        destinations.add(dest2);
        destinations.add(dest3);

        Mockito.when(destinationRepository.findAll()).thenReturn(destinations);

        assertEquals(3, destinationRepository.findAll().size());
    }

    @Test
    void getMyDestinations_WhenDestinationsIsNotPresent_ThenReturnEmptyList() {
        Mockito.when(destinationRepository.findDestinationsByUsername("Pesho")).thenReturn(destinations);

        assertEquals(0, destinationRepository.findDestinationsByUsername("Pesho").size());
    }

    @Test
    void getMyDestinations_WhenDestinationsIsPresent_ThenReturnDestinations() {
        Destination destination1 = new Destination() {{
            setCreator("Pesho");
        }};
        Destination destination2 = new Destination() {{
            setCreator("Pesho");
        }};
        Destination destination3 = new Destination() {{
            setCreator("Pesho");
        }};

        destinations.add(destination1);
        destinations.add(destination2);
        destinations.add(destination3);

        Mockito.when(destinationRepository.findDestinationsByUsername("Pesho")).thenReturn(destinations);

        assertEquals(3, destinationRepository.findDestinationsByUsername("Pesho").size());

    }


    @Test
    void getDestinationById_WhenDestinationIsNotPresent_ThenThrowException() {
        String id = "1";

        Mockito.when(destinationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                DestinationNotFoundException.class,
                () -> destinationService.getDestinationById(id));

    }


    @Test
    void getDestinationById_WhenDestinationIsPresent_ThenThrowException() {
        String id = "1";
        Destination destination = new Destination();
        destination.setTownName("Cushkovo");

        Mockito.when(destinationRepository.findById(id)).thenReturn(Optional.of(destination));

        DestinationServiceModel mockDestination = destinationService.getDestinationById(id);

        assertEquals(destination.getTownName(), mockDestination.getTownName());
    }


    @Test
    void editDestination_WhenDestinationIsNotPresent_ThenThrowException() {
        String id = "1";

        Mockito.when(destinationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DestinationNotFoundException.class,
                () -> destinationService.getDestinationById(id));

    }

    @Test
    void editDestination_WhenDestinationIsPresent_ThenThrowException() {
        String id = "1";
        DestinationServiceModel destinationServiceModel = new DestinationServiceModel();
        destinationServiceModel.setTownName("Chukurovo2");
        destinationServiceModel.setPopulation(222);
        destinationServiceModel.setAltitude(2222);
        destinationServiceModel.setDescription("2");

        Destination destination = new Destination();
        destination.setTownName("Chukurovo");
        destination.setPopulation(15000);
        destination.setAltitude(2500);
        destination.setDescription("BlaBla");

        Mockito.when(destinationRepository.findById(id)).thenReturn(Optional.of(destination));

        DestinationServiceModel mockDestinationServiceModel = destinationService.editDestination(id, destinationServiceModel);
        

    }

    // public DestinationServiceModel editDestination(String id, DestinationServiceModel destinationServiceModel) {
    //        Destination destination = this.destinationRepository.findById(id)
    //                .orElseThrow(() -> new DestinationNotFoundException(DestinationValidationConstants.DESTINATION_WITH_TOWN_ID_NOT_FOUND_EXCEPTION));
    //        destination.setImgUrl(destinationServiceModel.getImgUrl());
    //        destination.setTownName(destinationServiceModel.getTownName());
    //        destination.setPopulation(destinationServiceModel.getPopulation());
    //        destination.setAltitude(destinationServiceModel.getAltitude());
    //        destination.setDescription(destinationServiceModel.getDescription());
    //
    //        return this.modelMapper.map(this.destinationRepository.saveAndFlush(destination), DestinationServiceModel.class);
    //    }

    @Test
    void deleteDestination() {

    }

    private DestinationServiceModel getDestinationServiceModel() {

        DestinationServiceModel destinationServiceModel = new DestinationServiceModel();

        destinationServiceModel.setAltitude(15);
        destinationServiceModel.setCreator("Pesho");
        destinationServiceModel.setDescription("DESASAS");
        destinationServiceModel.setId("1");
        destinationServiceModel.setPopulation(1500);
        destinationServiceModel.setTownName("Varna");
        destinationServiceModel.setImgUrl("dsassdsdsdssdsdsd");

        return destinationServiceModel;

    }
}