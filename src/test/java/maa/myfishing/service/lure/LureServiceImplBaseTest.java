package maa.myfishing.service.lure;

import maa.myfishing.base.BaseTest;
import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.models.Lure;
import maa.myfishing.data.models.TypeOfLure;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.data.reposipories.LureRepository;
import maa.myfishing.service.models.LureServiceModel;
import maa.myfishing.service.serices.LureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LureServiceImplBaseTest extends BaseTest {
    List<Lure> lures = new ArrayList<>();
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

    List<Lure> getMockLures(){
        Fishing fishing = new Fishing();
        fishing.setId("1");
        Lure lure1 = new Lure();
        Lure lure2 = new Lure();
        Lure lure3 = new Lure();
        lure1.setFishing(fishing);
        lure2.setFishing(fishing);
        lure3.setFishing(fishing);
        lures.add(lure1);
        lures.add(lure2);
        lures.add(lure3);

        return lures;
    };

    protected Fishing getFishing() {
        Destination destination = new Destination();
        destination.setTownName("TestTownName");

        Fishing fishing = new Fishing();
        fishing.setDescription("Fishing Test Description");
        fishing.setDestination(destination);

        return fishing;
    }
}
