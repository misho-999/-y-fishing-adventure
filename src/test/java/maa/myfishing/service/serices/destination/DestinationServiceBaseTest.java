package maa.myfishing.service.serices.destination;

import maa.myfishing.base.TestBase;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.service.serices.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DestinationServiceBaseTest extends TestBase {
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
}
