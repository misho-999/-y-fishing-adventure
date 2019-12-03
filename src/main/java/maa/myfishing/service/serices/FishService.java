package maa.myfishing.service.serices;

import maa.myfishing.service.models.FishServiceModel;

import java.util.List;

public interface FishService {

    void createFish(FishServiceModel fishServiceModel, String fishingId);

   List<FishServiceModel> getAllFishesByFishingId(String Id);

   void deleteFish(String id);
}
