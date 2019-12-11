package maa.myfishing.service.serices.destination;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.service.models.DestinationServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class DestinationServiceTest extends DestinationServiceBaseTest {

    //OK
    @Test
    void createDestination() {
        DestinationServiceModel destinationServiceModel = getDestinationServiceModel();
        Destination destination = getDestination();

        Mockito.when(destinationRepository.saveAndFlush(any(Destination.class))).thenReturn(destination);

        DestinationServiceModel mockDestination = destinationService.createDestination(destinationServiceModel);

        assertNotNull(destination);
        assertEquals(destinationServiceModel.getTownName(), mockDestination.getTownName());
    }

    //OK
    @Test
    void getDestinationByTownName_WhenTownIsNotPresent_ShouldThrowException() {
        String townName = "Chushkovo";

        Mockito.when(destinationRepository.findByTownName(townName))
                .thenReturn(Optional.empty());

        assertThrows(
                DestinationNotFoundException.class,
                () -> destinationService.getDestinationByTownName(townName));
    }

    //OK
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

    //OK
    @Test
    void getAllDestinations_WhenDestinationsAreNotPresent_ShouldReturnEmptyList() {

        Mockito.when(destinationRepository.findAllByOrderByFishingsCountDesc()).thenReturn(destinations);

        List<DestinationServiceModel> mockAllDestinations = destinationService.getAllDestinations();

        assertEquals(0, mockAllDestinations.size());
    }

    //OK
    @Test
    void getAllDestinations_WhenDestinationsArePresent_ShouldReturnListFromDestinations() {
        List<Destination> destinations = getDestinations();

        Mockito.when(destinationRepository.findAllByOrderByFishingsCountDesc()).thenReturn(destinations);

        List<DestinationServiceModel> mockAllDestinations = destinationService.getAllDestinations();

        assertEquals(3, mockAllDestinations.size());
    }

    //OK
    @Test
    void getMyDestinations_WhenDestinationsIsNotPresent_ThenReturnEmptyList() {
        String username = "spring";
        Mockito.when(destinationRepository.findDestinationsByUsername(username)).thenReturn(destinations);

        List<DestinationServiceModel> mockDestinations = destinationService.getMyDestinations(username);

        assertEquals(0, mockDestinations.size());
    }

    //OK
    @Test
    void getMyDestinations_WhenDestinationsIsPresent_ThenReturnDestinations() {
        String username = "spring";
        List<Destination> destinations = getDestinations();

        Mockito.when(destinationRepository.findDestinationsByUsername(username)).thenReturn(this.destinations);

        List<DestinationServiceModel> mockDestinations = destinationService.getMyDestinations(username);

        assertEquals(3, mockDestinations.size());
    }

    //OK
    @Test
    void getDestinationById_WhenDestinationIsNotPresent_ShouldThrow() {
        String id = "id";

        assertThrows(DestinationNotFoundException.class,
                ()-> destinationService.getDestinationById(id));
    }

    //OK
    @Test
    void getDestinationById_WhenDestinationIsPresent_ThenThrowException() {
        String id = "1";
        Destination destination = getDestination();

        Mockito.when(destinationRepository.findById(id)).thenReturn(Optional.of(destination));

        DestinationServiceModel mockDestination = destinationService.getDestinationById(id);

        assertEquals(destination.getTownName(), mockDestination.getTownName());
    }

    //OK
    @Test
    void editDestination_WhenDestinationIsNotPresent_ThenThrowException() {
        testDestinationWithIdIsNotPresent();
    }

    //OK
    @Test
    void editDestination_WhenDestinationIsPresent_ThenThrowException() {
        String id = "1";
        DestinationServiceModel destinationServiceModel = getDestinationServiceModel();
        Destination destination = getDestination();

        Mockito.when(destinationRepository.findById(id)).thenReturn(Optional.of(destination));
        Mockito.when(destinationRepository.saveAndFlush(any(Destination.class))).thenReturn(destination);

        DestinationServiceModel mockDestinationServiceModel = destinationService.editDestination(id, destinationServiceModel);

        assertEquals("Varna", mockDestinationServiceModel.getTownName());
        assertEquals(15, mockDestinationServiceModel.getAltitude());
        assertEquals("TestDescriptionBlaBla", mockDestinationServiceModel.getDescription());
    }

    //OK
    @Test
    void deleteDestination_WhenDestinationIsNotPresent_ThenThrowException() {
        String id= "id";

        assertThrows(DestinationNotFoundException.class,
                ()->  destinationService.deleteDestination(id));
    }

    //OK
    @Test
    void deleteDestination_WhenDestinationIsPresent_ShouldDeleteDestination() {
        String id= "id";
        Fishing fishing = new Fishing();
        fishing.setDescription("FishingDescription");

        Destination destination = getDestination();
        destination.getFishings().add(fishing);

        Mockito.when(destinationRepository.findById(id)).thenReturn(Optional.of(destination));

        destinationService.deleteDestination(id);

        Mockito.verify(fishingRepository, times(1)).delete(fishing);

        Mockito.verify(destinationRepository, times(1)).delete(destination);
    }

    //OK
    @Test
    void getTopFiveDestination_WhenDestinationsIsPresent_ShouldReturnDestinations(){
        List<Destination> destinations = getDestinations();

        Mockito.when(destinationRepository.findTop5ByOrderByFishingsCountDesc()).thenReturn(destinations);

        List<DestinationServiceModel> mockDestinations = destinationService.getTopFiveDestination();
        assertEquals(3, mockDestinations.size());
    }

    @Test
    void getTopFiveDestination_WhenDestinationsNotIsPresent_ShouldReturnEmpyList(){
        List<DestinationServiceModel> mockDestinations = destinationService.getTopFiveDestination();
        assertEquals(0, mockDestinations.size());
    }

    private DestinationServiceModel getDestinationServiceModel() {
        DestinationServiceModel destinationServiceModel = new DestinationServiceModel();

        destinationServiceModel.setAltitude(15);
        destinationServiceModel.setCreator("Pesho");
        destinationServiceModel.setDescription("TestDescriptionBlaBla");
        destinationServiceModel.setId("1");
        destinationServiceModel.setPopulation(1500);
        destinationServiceModel.setTownName("Varna");
        destinationServiceModel.setImgUrl("dsassdsdsdssdsdsd");

        return destinationServiceModel;
    }

    private Destination getDestination() {
        Destination destination = new Destination();

        destination.setAltitude(15);
        destination.setCreator("Pesho");
        destination.setDescription("TestDescriptionBlaBla");
        destination.setId("1");
        destination.setPopulation(1500);
        destination.setTownName("Varna");
        destination.setImgUrl("dsassdsdsdssdsdsd");

        return destination;
    }

    private List<Destination> getDestinations() {
        Destination destination1 = new Destination();
        Destination destination2 = new Destination();
        Destination destination3 = new Destination();
        destination1.setTownName("Sofia1");
        destination2.setTownName("Sofia2");
        destination3.setTownName("Sofia3");
        destination1.setId("Id1");
        destination2.setId("Id2");
        destination3.setId("Id3");

        destinations.add(destination1);
        destinations.add(destination2);
        destinations.add(destination3);

        return destinations;
    }
}