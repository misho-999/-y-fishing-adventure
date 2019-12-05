package maa.myfishing.service.serices.fishing;

import maa.myfishing.base.TestBase;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fish;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.data.reposipories.LureRepository;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.eroors.FishingNotFoundException;
import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.models.FishingServiceModel;
import maa.myfishing.service.serices.DestinationService;
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

public class FishingBaseTest extends TestBase {
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
}
