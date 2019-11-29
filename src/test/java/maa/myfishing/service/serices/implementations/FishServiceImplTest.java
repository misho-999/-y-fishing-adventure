package maa.myfishing.service.serices.implementations;

import maa.myfishing.data.models.Fish;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.FishRepository;
import maa.myfishing.service.models.FishingServiceModel;
import maa.myfishing.service.serices.FishingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FishServiceImplTest {
    List<Fish> fishes;
    FishRepository fishRepository;
    FishingService fishingService;
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        fishes = new ArrayList<>();
        fishRepository = Mockito.mock(FishRepository.class);
        fishingService = Mockito.mock(FishingService.class);
        modelMapper = new ModelMapper();

        Mockito.when(fishRepository.findAllByFishingId("1")).thenReturn(fishes);

        FishingServiceModel fishingServiceModel = new FishingServiceModel();
    }

    @Test
    void createFish() {

    }


    @Test
    void getAllFishesByFishingId_whenIdIsNotPresent_ShouldReturnEmptyList() {
        List<Fish> fishing = fishRepository.findAllByFishingId("2");
        assertEquals(0, fishing.size());
    }

    @Test
    void getAllFishesByFishingId_whenIdIsIsPresent_ShouldReturnCorrectResult() {
        Fish fish1 = new Fish();
        Fish fish2 = new Fish();
        Fish fish3 = new Fish();

        fishes.add(fish1);
        fishes.add(fish2);
        fishes.add(fish3);

        Fishing fishing = new Fishing() {{
            setId("1");
            setFishes(fishes);
        }};

        assertEquals(fishes.size(), fishRepository.findAllByFishingId("1").size());
    }
}