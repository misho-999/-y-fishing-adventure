package maa.myfishing.service.serices.destination;

import maa.myfishing.data.models.Destination;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.service.models.DestinationServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DestinationServiceTest extends DestinationBaseTest {

    @Test
    void createDestination() {
        DestinationServiceModel destinationServiceModel = getDestinationServiceModel();

        destinationService.createDestination(destinationServiceModel);

        ArgumentCaptor<Destination> argument = ArgumentCaptor.forClass(Destination.class);
        Mockito.verify(destinationRepository).save(argument.capture());

        Destination destination = argument.getValue();

        assertNotNull(destination);
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
    void getDestinationByTownName_WhenTownIsExist_ShouldReturnDestination() {
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
        testDestinationWithIdIsNotPresent();
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
        testDestinationWithIdIsNotPresent();
    }

    @Test
    void editDestination_WhenDestinationIsPresent_ThenThrowException() {
        String id = "1";
        DestinationServiceModel destinationServiceModel = new DestinationServiceModel();
        destinationServiceModel.setTownName("Chukurovo2");
        destinationServiceModel.setPopulation(22);
        destinationServiceModel.setAltitude(222);
        destinationServiceModel.setDescription("BlaBla2");
        destinationServiceModel.setImgUrl("URL2");

        Destination destination = new Destination();
        destination.setTownName("Chukurovo1");
        destination.setPopulation(111111);
        destination.setAltitude(1111);
        destination.setDescription("BlaBla1");
        destination.setImgUrl("URL1");

        Mockito.when(destinationRepository.findById(id)).thenReturn(Optional.of(destination));

        DestinationServiceModel mockDestinationServiceModel = destinationService.editDestination(id, destinationServiceModel);

        assertEquals("Chukurovo2", mockDestinationServiceModel.getTownName());
        assertEquals(150002, mockDestinationServiceModel.getPopulation());
        assertEquals(2502, mockDestinationServiceModel.getAltitude());
        assertEquals("BlaBla2", mockDestinationServiceModel.getDescription());
        assertEquals("URL2", mockDestinationServiceModel.getImgUrl());
    }

    @Test
    void deleteDestination_WhenDestinationIsNotPresent_ThenThrowException() {
        testDestinationWithIdIsNotPresent();
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