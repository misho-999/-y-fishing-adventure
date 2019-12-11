package maa.myfishing.service.serices.fishing;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fish;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.eroors.FishingAlreadyExistsException;
import maa.myfishing.eroors.FishingNotFoundException;
import maa.myfishing.service.models.FishingServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class FishingServiceImplTest extends FishingBaseTest {

    @Test
    void addFishingToDestination_WhenFishingIsAlreadyExist_ShouldThrowException() {
        String townName = "Cushkovo";
        FishingServiceModel fishingServiceModel = new FishingServiceModel();
        fishingServiceModel.setDate(LocalDate.of(2019, 12, 12));
        fishingServiceModel.setDescription("Description");

        Fishing fishing = new Fishing();
        fishing.setDate(LocalDate.of(2019, 12, 12));
        fishing.setDescription("Description");

        Mockito.when(fishingRepository.findByDateAndDescription(LocalDate.of(2019, 12, 12)
                , "Description")).thenReturn(Optional.of(fishing));

        assertThrows(FishingAlreadyExistsException.class,
                () -> fishingService.addFishingToDestination(fishingServiceModel, townName));
    }

    @Test
    void addFishingToDestination_WhenDestinationIdIsNotValid_ShouldThrowException() {
        String townName = "Cushkovo";
        FishingServiceModel fishingServiceModel = new FishingServiceModel();
        fishingServiceModel.setDate(LocalDate.of(2019, 12, 12));
        fishingServiceModel.setDescription("Description");

        Mockito.when(destinationRepository.findByTownName(townName)).thenReturn(Optional.empty());

        assertThrows(DestinationNotFoundException.class,
                () -> fishingService.addFishingToDestination(fishingServiceModel, townName));
    }

    @Test
    void addFishingToDestination_WhenDestinationIsAdded_CountOfFishingShouldIncrease() {
        String townName = "Cushkovo";

        FishingServiceModel fishingServiceModel = new FishingServiceModel();
        fishingServiceModel.setDate(LocalDate.of(2019, 12, 12));
        fishingServiceModel.setDescription("Description");

        Destination destination = new Destination();
        destination.setTownName("Cushkovo");

        Mockito.when(destinationRepository.findByTownName(townName)).thenReturn(Optional.of(destination));

        Mockito.when(fishingRepository.findAllFishingByTownName(destination.getTownName())).thenReturn(new ArrayList<>());

        FishingServiceModel mockFishingServiceModel = fishingService.addFishingToDestination(fishingServiceModel, townName);

        assertEquals(1, destination.getFishingsCount());
    }

    @Test
    void getAllFishings_WhenFishingsIsNotPresent_ShouldReturnEmptyList() {
        Mockito.when(fishingRepository.findAll()).thenReturn(fishings);

        List<FishingServiceModel> mockFishings = fishingService.getAllFishings();

        assertEquals(0, mockFishings.size());
    }

//    @Test
//    void getAllFishings_WhenFishingsIsPresent_ShouldReturnFishings() {
//        List<Fishing> fishings = getFishings();
//
//        Mockito.when(fishingRepository.findAll()).thenReturn(this.fishings);
//
//        List<FishingServiceModel> mockFishings = fishingService.getAllFishings();
//
//        assertEquals(2, mockFishings.size());
//    }

    @Test
    void getAllFishingsByTownName_WhenFishingAreNotPresent_ShouldReturnEmptyList() {
        String townName = "Cushkovo";
        Mockito.when(fishingRepository.findAllFishingByTownName(townName)).thenReturn(fishings);

        List<FishingServiceModel> mockFishings = fishingService.getAllFishingsByTownName(townName);

        assertEquals(0, mockFishings.size());
    }

    @Test
    void getAllFishingsByTownName_WhenFishingArePresent_ShouldReturnEmptyList() {
        String townName = "Cushkovo";
        List<Fishing> fishings = getFishings();

        Mockito.when(fishingRepository.findAllFishingByTownName(townName)).thenReturn(fishings);

        List<FishingServiceModel> mockFishings = fishingService.getAllFishingsByTownName(townName);

        assertEquals(2, mockFishings.size());
    }

    @Test
    void getAllFishingsByUsername_WhenFishingAreNotPresent_ShouldReturnEmptyList() {
        String username = "chushka";

        Mockito.when(fishingRepository.findAllFishingByUsername(username)).thenReturn(fishings);

        List<FishingServiceModel> mockFishings = fishingService.getAllFishingsByUsername(username);

        assertEquals(0, mockFishings.size());
    }

    @Test
    void getAllFishingsByUsername_WhenFishingArePresent_ShouldReturnEmptyList() {
        String username = "chushka";

        List<Fishing> fishings = getFishings();

        Mockito.when(fishingRepository.findAllFishingByUsername(username)).thenReturn(fishings);

        List<FishingServiceModel> mockFishings = fishingService.getAllFishingsByUsername(username);

        assertEquals(2, mockFishings.size());
    }

    @Test
    void getAllFishingsByUsernameAndTownName_WhenFishingAreNotPresent_ShouldReturnFishing() {
        String username = "Mitko";
        String townName = "Chushkovo";

        Mockito.when(fishingRepository.findAllFishingsByUsernameAndTownName(username, townName)).thenReturn(fishings);

        List<FishingServiceModel> mockFishings = fishingService.getAllFishingsByUsernameAndTownName(username, townName);

        assertEquals(0, mockFishings.size());
    }


    @Test
    void getAllFishingsByUsernameAndTownName_WhenFishingArePresent_ShouldReturnFishing() {
        String username = "Mitko";
        String townName = "Chushkovo";
        List<Fishing> fishings = getFishings();

        Mockito.when(fishingRepository.findAllFishingsByUsernameAndTownName(username, townName)).thenReturn(fishings);

        List<FishingServiceModel> mockFishings = fishingService.getAllFishingsByUsernameAndTownName(username, townName);

        assertEquals(2, mockFishings.size());
    }

    //OK
    @Test
    void getFishingById_WhenFishingIsNotPresent_ShouldThrowException() {
        String fishingId = "1";

        Mockito.when(fishingRepository.findById(fishingId)).thenReturn(Optional.empty());

        assertThrows(FishingNotFoundException.class,
                () -> fishingService.getFishingById(fishingId));
    }

    //OK
    @Test
    void getFishingById_WhenFishingIsPresent_ShouldReturnFishing() {
        String fishingId = "1";
        Fishing fishing = getFishing();

        Mockito.when(fishingRepository.findById(fishingId)).thenReturn(Optional.of(fishing));

        FishingServiceModel mockFishing = fishingService.getFishingById(fishingId);

        assertEquals(fishing.getDescription(), mockFishing.getDescription());
    }

    //OK
    @Test
    void deleteFishing_WhenFishingIsNotPresent_ShouldThrowException() {
        String fishingId = "1";
        String townName = "Chushkovo";

        Mockito.when(fishingRepository.findById(fishingId)).thenReturn(Optional.empty());

        assertThrows(FishingNotFoundException.class,
                () -> fishingService.deleteFishing(fishingId, townName));
    }


    //OK
    @Test
    void deleteFishing_WhenTownIsNotPresent_ShouldThrowException() {
        String fishingId = "1";
        String townName = "Chushkovo";

        Mockito.when(fishingRepository.findById(fishingId)).thenReturn(Optional.of(new Fishing()));
        Mockito.when(destinationRepository.findByTownName(townName)).thenReturn(Optional.empty());

        assertThrows(DestinationNotFoundException.class,
                () -> fishingService.deleteFishing(fishingId, townName));
    }

    //OK
    @Test
    void deleteFishing_WhenTownAndFishingArePresent_ShouldDeleteFishing() {
        String fishingId = "1";
        String townName = "Chushkovo";
        Fishing fishing = getFishing();

        Mockito.when(this.fishingRepository.findById(fishingId)).thenReturn(Optional.of(fishing));
        Mockito.when(destinationRepository.findByTownName(townName)).thenReturn(Optional.of(new Destination()));

        fishingService.deleteFishing(fishingId, townName);

        Mockito.verify(fishingRepository, times(1)).delete(fishing);
    }

    @Test
    void getTopFiveFishings() {
        List<Fishing> fishings = getFishings();

        Mockito.when(fishingRepository.findAllByOrderByDateDesc()).thenReturn(fishings);

        List<FishingServiceModel> mockFishings = fishingService.getTopFiveFishings();

        assertEquals(2, mockFishings.size());
    }
    //   @Override
    //    public List<FishingServiceModel> getTopFiveFishings() {
    //        List<Fishing> allFishings = this.fishingRepository.findAllByOrderByDateDesc();
    //
    //        List<FishingServiceModel> fishingServiceModels = this.setTownName(allFishings);
    //
    //        return this.setCountOfFishingsAndLures(fishingServiceModels)
    //                .stream().sorted((f1, f2) -> Integer.compare(f2.getCountOfFishes(), f1.getCountOfFishes()))
    //                .limit(5)
    //                .collect(Collectors.toList());
    //    }

    private List<Fishing> getFishings() {
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

    private Fishing getFishing() {
        Fishing fishing = new Fishing();
        Destination destination = new Destination();
        destination.setTownName("TestTownName");

        fishing.setDescription("Test description");
        fishing.setDestination(destination);

        return fishing;
    }
}