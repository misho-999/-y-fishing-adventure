package maa.myfishing.service.serices.implementations;

import jdk.dynalink.linker.LinkerServices;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.data.reposipories.UserInfoRepository;
import maa.myfishing.eroors.DestinationNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DestinationServiceImplTest {
    List<Destination> destinations;

    DestinationRepository destinationRepository;
    FishingRepository fishingRepository;
    UserInfoRepository userInfoRepository;

    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        destinations = new ArrayList<>();

        destinationRepository = Mockito.mock(DestinationRepository.class);
        fishingRepository = Mockito.mock(FishingRepository.class);
        userInfoRepository = Mockito.mock(UserInfoRepository.class);
        modelMapper = new ModelMapper();

        Mockito.when(destinationRepository.findAll()).thenReturn(destinations);

    }

    @Test
    void createDestination() {
    }

    @Test
    void getDestinationByTownName() {

    }

    @Test
    void getDestinationByTownName_IfDestinationIstPresent_ShouldReturnDestination() {
        Destination destination = new Destination();
        Mockito.when(destinationRepository.findByTownName("Varna")).thenReturn(Optional.of(destination));

        assertEquals(destination, destinationRepository.findByTownName("Varna").get());
    }

    @Test
    void getDestinationByTownName_IfDestinationIsNotPresent_ShouldThrowDestinationNotFoundException() {
        Mockito.when(destinationRepository.findByTownName("Chokurino")).thenReturn(null);

        assertNull(destinationRepository.findByTownName("Chokurino"));
    }

    @Test
    void getAllDestinations_WhenDestinationsAreNotPresent_ShouldReturnEmptyList() {
        assertEquals(0, destinationRepository.findAll().size());
    }

    @Test
    void getAllDestinations_WhenDestinationsArePresent_ShouldReturnCorrectResult() {
        Destination dest1 = new Destination();
        Destination dest2 = new Destination();
        Destination dest3 = new Destination();
        destinations.add(dest1);
        destinations.add(dest2);
        destinations.add(dest3);
        assertEquals(3, destinationRepository.findAll().size());
    }

    @Test
    void getMyDestinations() {
    }

    @Test
    void getDestinationById() {
    }

    @Test
    void editDestination() {
    }

    @Test
    void deleteDestination() {
    }

    @Test
    void getTopFiveDestination() {
    }
}