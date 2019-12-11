package maa.myfishing.service.serices.fish;

import javafx.beans.binding.When;
import maa.myfishing.base.TestBase;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fish;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.FishRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.eroors.FishNotFoundException;
import maa.myfishing.eroors.FishingNotFoundException;
import maa.myfishing.service.models.FishServiceModel;
import maa.myfishing.service.serices.FishService;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class FishServiceImplTest extends TestBase {
    List<Fish> fishes = new ArrayList<>();

    @MockBean
    FishRepository fishRepository;

    @MockBean
    FishingRepository fishingRepository;

    @Autowired
    FishService fishService;

    @Autowired
    Validator validator;

    //OK
    @Test
    void createFish_WhenFishingIdIsNotValid_ShouldThrowException() {
        String fishingId = "1";
        FishServiceModel fishServiceModel = new FishServiceModel();
        fishServiceModel.setWeightInKilograms(10.50);
        fishServiceModel.setLengthInCentimeters(55);

        assertThrows(FishingNotFoundException.class,
                () -> fishService.createFish(fishServiceModel, "1"));
    }

    @Test
    void createFish_WhenFishIsValid_ShouldThrowException() {
        String fishingId = "1";
        Destination destination = new Destination();
        destination.setTownName("Test");

        FishServiceModel fishServiceModel = new FishServiceModel();
        fishServiceModel.setWeightInKilograms(10.50);
        fishServiceModel.setLengthInCentimeters(55);

        Fishing fishing = new Fishing();
        fishing.setDescription("FishingDescription");
        fishing.setDestination(destination);

        Fish fish = new Fish();
        fish.setFishName("Tune");
        fish.setWeightInKilograms(10.50);
        fish.setLengthInCentimeters(55);

        fish.setFishing(fishing);

        Mockito.when(fishingRepository.findById(fishingId)).thenReturn(Optional.of(fishing));
        Mockito.when(fishRepository.saveAndFlush(any(Fish.class))).thenReturn(fish);

        FishServiceModel mockFish = fishService.createFish(fishServiceModel, fishingId);

        assertThat(mockFish, is(notNullValue()));
    }

    //OK
    @Test
    void getAllFishesByFishingId_WhenFishesArePresentShouldReturnFishes() {
        String id = "id";
        List<Fish> fishes = getFishes();
        Mockito.when(fishRepository.findAllByFishingIdOrderByWeightInKilogramsDesc(id)).thenReturn(fishes);

        List<FishServiceModel> mockFishes = fishService.getAllFishesByFishingId(id);

        assertEquals(3, mockFishes.size());
    }

    //OK
    @Test
    void getAllFishesByFishingId_WhenFishesAreNotPresentShouldReturnFishes() {
        String id = "id";
        Mockito.when(fishRepository.findAllByFishingIdOrderByWeightInKilogramsDesc(id)).thenReturn(fishes);

        List<FishServiceModel> mockFishes = fishService.getAllFishesByFishingId(id);

        assertEquals(0, mockFishes.size());
    }

    //OK
    @Test
    void deleteFish_WhenFishIsNotPresent_ShouldThrowException() {
        String id = "id";

        assertThrows(FishNotFoundException.class,
                () -> fishService.deleteFish(id));
    }

    @Test
    void deleteFish_WhenFishIsPresent_ShouldDeleteFish() {
        String id = "id";
        Fish fish = new Fish();

        Mockito.when(this.fishRepository.findById(id)).thenReturn(Optional.of(fish));

        fishService.deleteFish(id);

        Mockito.verify(fishRepository, times(1)).delete(fish);
    }

    //OK
    @Test
    void getAllFishes_WhenFishesArePresent_ShouldReturnFishes() {
        List<Fish> fishes = getFishes();

        Mockito.when(fishRepository.findAllByOrderByWeightInKilogramsDesc()).thenReturn(fishes);

        List<FishServiceModel> mockFishes = fishService.getAllFishes();

        assertEquals(3, mockFishes.size());
        assertEquals("Fish1", mockFishes.get(0).getFishName());
    }

    //OK
    @Test
    void getTopFiveFishes_WhenFishesArePresent_ShouldReturnFishes(){
        List<Fish> fishes = getFishes();

        Mockito.when(fishRepository.findTop5ByOrderByWeightInKilogramsDesc()).thenReturn(fishes);

        List<FishServiceModel> mockFishes = fishService.getTopFiveFishes();

        assertEquals(3, mockFishes.size());
        assertEquals("Fish1", mockFishes.get(0).getFishName());
    }
    //   @Override
    //    public List<FishServiceModel> getTopFiveFishes() {
    //        return this.fishRepository.findTop5ByOrderByWeightInKilogramsDesc()
    //                .stream()
    //                .map(f -> this.modelMapper.map(f, FishServiceModel.class))
    //                .collect(Collectors.toList());
    //    }


    private List<Fish> getFishes() {
        Fishing fishing1 = new Fishing();
        Fishing fishing2 = new Fishing();
        Fishing fishing3 = new Fishing();

        fishing1.setId("1");
        fishing2.setId("2");
        fishing3.setId("3");

        Fish fish1 = new Fish();
        Fish fish2 = new Fish();
        Fish fish3 = new Fish();
        fish1.setFishName("Fish1");
        fish2.setFishName("Fish2");
        fish3.setFishName("Fish3");

        fish1.setFishing(fishing1);
        fish2.setFishing(fishing2);
        fish3.setFishing(fishing3);

        fishes.add(fish1);
        fishes.add(fish2);
        fishes.add(fish3);

        return fishes;
    }

}