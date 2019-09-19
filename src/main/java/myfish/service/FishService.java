package myfish.service;


import myfish.domain.modules.service.FishServiceModel;

public interface FishService {

    boolean registerFish(FishServiceModel fishServiceModel);

    boolean loginFish(FishServiceModel fishServiceModel);
}
