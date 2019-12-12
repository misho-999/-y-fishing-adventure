package maa.myfishing.service.lure;

import maa.myfishing.data.models.*;
import maa.myfishing.eroors.FishingNotFoundException;
import maa.myfishing.eroors.LureAlreadyExistsException;
import maa.myfishing.service.models.LureServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class LureServiceImplTest extends LureServiceImplBaseTest {

    @Test
    void createLure_WhenLuresIsValid_ShouldCreateLure() {
        String fishingId = "id";
        Lure lure = getLure();
        LureServiceModel lureServiceModel = getLureServiceModel();
        Fishing fishing = getFishing();

        Mockito.when(lureRepository.saveAndFlush(any(Lure.class))).thenReturn(lure);
        Mockito.when(fishingRepository.findById(fishingId)).thenReturn(Optional.of(fishing));

        LureServiceModel mockLure = lureService.createLure(lureServiceModel, fishingId);

        assertNotNull(mockLure);
        assertEquals(lureServiceModel.getColor(), mockLure.getColor());
    }

    @Test
    void createLure_WhenLuresIsExist_ShouldThrowException() {
        String fishingId = "id";
        String make = "Rapala";
        String model = "Original";
        Integer length = 50;
        Lure lure = getLure();
        LureServiceModel lureServiceModel = getLureServiceModel();
        Fishing fishing = getFishing();

        Mockito.when(lureRepository.findByMakeAndModelAndLengthInMillimeters(make, model, length)).thenReturn(Optional.of(lure));

        Mockito.when(fishingRepository.findById(fishingId)).thenReturn(Optional.of(fishing));

        assertThrows(LureAlreadyExistsException.class,
                () -> lureService.createLure(lureServiceModel, fishingId));
    }

    @Test
    void createLure_WhenFishingIsNotExist_ShouldThrowException() {
        String fishingId = "id";
        LureServiceModel lureServiceModel = getLureServiceModel();

        Mockito.when(fishingRepository.findById(fishingId)).thenReturn(Optional.empty());

        assertThrows(FishingNotFoundException.class,
                () -> lureService.createLure(lureServiceModel, fishingId));
    }

    @Test
    void getAllLuresByFishingId_WhenFishingIsNotPresent_ShouldReturnEmptyList() {
        String fishingId = "id";

        Mockito.when(lureRepository.findAllLuresByFishingId(fishingId)).thenReturn(lures);

        List<LureServiceModel> lures = lureService.getAllLuresByFishingId(fishingId);

        assertEquals(0, lures.size());
    }

    @Test
    void getAllLuresByFishingId_WhenFishingIsPresentAndLureArePresent_ShouldReturnLures() {
        String fishingId = "id";

        List<Lure> lures = getMockLures();

        Mockito.when(lureRepository.findAllLuresByFishingId(fishingId)).thenReturn(this.lures);

        List<LureServiceModel> mockLures = lureService.getAllLuresByFishingId(fishingId);

        assertEquals(3, lures.size());
    }

    @Test
    void deleteLure_WhenLureIsPresent() {
        String id = "id";
        Lure lure = getLure();

        Mockito.when(lureRepository.findById(id)).thenReturn(Optional.of(lure));

        lureService.deleteLure(id);

        Mockito.verify(lureRepository, times(1)).delete(lure);
    }

    @Test
    void getAllLures_WhenLuresAreNotPresent_ShouldReturnEmptyList() {

        Mockito.when(lureRepository.findAll()).thenReturn(lures);

        List<LureServiceModel> mockLures = lureService.getAllLures();

        assertEquals(0, mockLures.size());
    }

    @Test
    void getAllLures_WhenLuresArePresent_ShouldReturnLures() {

        Mockito.when(lureRepository.findAll()).thenReturn(getMockLures());

        List<LureServiceModel> mockLures = lureService.getAllLures();

        assertEquals(3, mockLures.size());
    }
}