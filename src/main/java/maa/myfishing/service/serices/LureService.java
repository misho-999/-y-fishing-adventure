package maa.myfishing.service.serices;

import com.sun.source.doctree.SeeTree;
import maa.myfishing.service.models.FishServiceModel;
import maa.myfishing.service.models.LureServiceModel;

import java.util.List;

public interface LureService {

    void createLure(LureServiceModel lureServiceModel, String fishingId);

    List<LureServiceModel> getAllLuresByFishingId(String fishingId);

    List<LureServiceModel> getAllLures();

    void deleteLure(String id);
}
