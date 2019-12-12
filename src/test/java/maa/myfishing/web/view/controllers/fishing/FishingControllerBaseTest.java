package maa.myfishing.web.view.controllers.fishing;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.data.reposipories.FishingRepository;
import maa.myfishing.service.serices.CloudinaryService;
import maa.myfishing.service.serices.FishingService;
import maa.myfishing.web.base.ViewBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class FishingControllerBaseTest extends ViewBaseTest {

    List<Fishing> fishings = new ArrayList<>();

    @MockBean
    FishingRepository fishingRepository;

    @MockBean
    DestinationRepository destinationRepository;

    @Autowired
    FishingService fishingService;

    @Autowired
    CloudinaryService cloudinaryService;

    protected List<Fishing> getMockFishings() {
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

    protected Fishing getMockFishing() {
        Destination destination = new Destination();
        destination.setTownName("Chushkovo1");

        Fishing fishing = new Fishing();
        fishing.setDestination(destination);

        fishings.add(fishing);

        return fishing;
    }

    protected Destination getMockDestination() {
        Destination destination = new Destination();
        destination.setTownName("Chushkovo");
        destination.setFishings(getMockFishings());

        return destination;
    }
}
