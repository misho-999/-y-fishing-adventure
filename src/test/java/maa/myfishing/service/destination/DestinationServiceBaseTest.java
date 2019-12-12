package maa.myfishing.service.destination;

import maa.myfishing.base.BaseTest;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.serices.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DestinationServiceBaseTest extends BaseTest {
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
    void testDestinationWithIdIsNotPresent() {
        String id = "id";

        assertThrows(
                DestinationNotFoundException.class,
                () -> destinationService.getDestinationById(id));
    }


    protected DestinationServiceModel getDestinationServiceModel() {
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

    protected Destination getDestination() {
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

    protected List<Destination> getDestinations() {
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
