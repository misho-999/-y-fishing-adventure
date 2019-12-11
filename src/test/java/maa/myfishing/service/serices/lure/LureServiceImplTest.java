package maa.myfishing.service.serices.lure;

import maa.myfishing.base.TestBase;
import maa.myfishing.data.models.*;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.data.reposipories.LureRepository;
import maa.myfishing.eroors.FishingNotFoundException;
import maa.myfishing.eroors.LureAlreadyExistsException;
import maa.myfishing.service.models.LureServiceModel;
import maa.myfishing.service.serices.FishingService;
import maa.myfishing.service.serices.LureService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class LureServiceImplTest extends LureServiceImplBaseTest{


    //OK
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

    //OK
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
            ()-> lureService.createLure(lureServiceModel, fishingId));
    }

    //OK
    @Test
    void createLure_WhenFishingIsNotExist_ShouldThrowException() {
        String fishingId = "id";
        LureServiceModel lureServiceModel = getLureServiceModel();

        Mockito.when(fishingRepository.findById(fishingId)).thenReturn(Optional.empty());

        assertThrows(FishingNotFoundException.class,
                ()-> lureService.createLure(lureServiceModel, fishingId));
    }

    @Test
    void getAllLuresByFishingId_WhenFishingIsNotPresent(){

    }
    //         public List<LureServiceModel> getAllLuresByFishingId(String fishingId) {
    //        List<Lure> lures = this.lureRepository.findAllLuresByFishingId(fishingId);
    //
    //        return lures.stream()
    //                .map(l -> modelMapper.map(l, LureServiceModel.class))
    //                .collect(Collectors.toList());
    //    }

}