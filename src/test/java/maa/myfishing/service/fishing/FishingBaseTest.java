package maa.myfishing.service.fishing;

import maa.myfishing.base.BaseTest;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.data.reposipories.LureRepository;
import maa.myfishing.eroors.FishingNotFoundException;
import maa.myfishing.service.serices.FishingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FishingBaseTest extends BaseTest {
    List<Fishing> fishings;

    @MockBean
    FishingRepository fishingRepository;

    @MockBean
    FishRepository fishRepository;

    @MockBean
    LureRepository lureRepository;

    @MockBean
    DestinationRepository destinationRepository;

    @MockBean
    Validator validator;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    FishingService fishingService;

    @BeforeEach
    private void setupTest() {
        fishings = new ArrayList<>();
    }


    @Test
    void testFishingWithIdIsNotPresent() {
        String id = "1";

        Mockito.when(fishingRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                FishingNotFoundException.class,
                () -> fishingService.getFishingById(id));
    }

    protected List<Fishing> getFishings() {
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

    protected Fishing getFishing() {
        Fishing fishing = new Fishing();
        Destination destination = new Destination();
        destination.setTownName("TestTownName");

        fishing.setDescription("Test description");
        fishing.setDestination(destination);

        return fishing;
    }

}
