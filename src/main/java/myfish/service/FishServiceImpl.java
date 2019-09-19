package myfish.service;

import myfish.domain.entities.Fish;
import myfish.domain.modules.service.FishServiceModel;
import myfish.repository.FishRepository;
import myfish.util.ModelMapper;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;

public class FishServiceImpl implements FishService {

    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;

    @Inject
    public FishServiceImpl(FishRepository fishRepository, myfish.util.ModelMapper modelMapper) {
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerFish(FishServiceModel fishServiceModel) {
        Fish fish = this.modelMapper.map(fishServiceModel, Fish.class);

        try {
            this.fishRepository.save(fish);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean loginFish(FishServiceModel fishServiceModel) {
//        if (this.fishRepository.findByUsernameAndPassword(userServiceModel.getUsername(), DigestUtils.sha256Hex(userServiceModel.getPassword())) != null) {
//            return true;
//        }

        return false;
    }


}
