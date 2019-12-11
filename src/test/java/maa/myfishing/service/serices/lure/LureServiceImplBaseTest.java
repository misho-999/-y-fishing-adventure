package maa.myfishing.service.serices.lure;

import maa.myfishing.base.TestBase;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.models.Lure;
import maa.myfishing.data.models.TypeOfLure;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.data.reposipories.LureRepository;
import maa.myfishing.eroors.FishingNotFoundException;
import maa.myfishing.service.models.LureServiceModel;
import maa.myfishing.service.serices.LureService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LureServiceImplBaseTest extends TestBase {

    @MockBean
    LureRepository lureRepository;

    @MockBean
    FishingRepository fishingRepository;

    @Autowired
    LureService lureService;

    protected LureServiceModel getLureServiceModel() {
        LureServiceModel lureServiceModel = new LureServiceModel();
        lureServiceModel.setTypeOfLure(TypeOfLure.floating);
        lureServiceModel.setFishingId("1");
        lureServiceModel.setColor("Silver");
        lureServiceModel.setMake("Rapala");
        lureServiceModel.setModel("Original");
        lureServiceModel.setLengthInMillimeters(50);

        return lureServiceModel;
    }

    protected Lure getLure() {
        Fishing fishing = getFishing();

        Lure lure = new Lure();
        lure.setTypeOfLure(TypeOfLure.floating);
        lure.setFishing(fishing);
        lure.setColor("Silver");

        return lure;
    }

    protected Fishing getFishing() {
        Destination destination = new Destination();
        destination.setTownName("TestTownName");

        Fishing fishing = new Fishing();
        fishing.setDescription("Fishing Test Description");
        fishing.setDestination(destination);

        return fishing;
    }
}
