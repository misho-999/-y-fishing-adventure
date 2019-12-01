package maa.myfishing.service.serices.implementations;

import maa.myfishing.constants.validation.LureValidationConstants;
import maa.myfishing.data.models.Fishing;
import maa.myfishing.data.models.Lure;
import maa.myfishing.data.reposipories.LureRepository;
import maa.myfishing.eroors.LureAlreadyExistsException;
import maa.myfishing.service.models.FishingServiceModel;
import maa.myfishing.service.models.LureServiceModel;
import maa.myfishing.service.serices.FishingService;
import maa.myfishing.service.serices.LureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LureServiceImpl implements LureService {
    private final LureRepository lureRepository;
    private final FishingService fishingService;
    private final ModelMapper modelMapper;

    @Autowired
    public LureServiceImpl(LureRepository lureRepository, FishingService fishingService, ModelMapper modelMapper) {
        this.lureRepository = lureRepository;
        this.fishingService = fishingService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createLure(LureServiceModel lureServiceModel) {

        Lure lure = this.lureRepository.findByMakeAndModelAndLengthInMillimeters(lureServiceModel.getMake(),
                lureServiceModel.getModel(), lureServiceModel.getLengthInMillimeters()).orElse(null);

        if (lure != null) {
            throw new LureAlreadyExistsException(LureValidationConstants.LURE_ALREADY_EXIST_EXCEPTION);
        }

        lure = this.modelMapper.map(lureServiceModel, Lure.class);

        FishingServiceModel fishingServiceModel = this.fishingService.getFishingById("rrrrrrrrrr");

        Fishing fishing = this.modelMapper.map(fishingServiceModel, Fishing.class);

        lure.setFishing(fishing);

        this.lureRepository.saveAndFlush(lure);

        // Fishing fishing = this.fishingRepository
        //                .findByDateAndDescription(fishingServiceModel.getDate(), fishingServiceModel.getDescription())
        //                .orElse(null);
        //
        //        if (fishing != null) {
        //            throw new FishingAlreadyExistsException(FishingValidationConstants.FISHING_ALREADY_EXIST_EXCEPTION);
        //        }
        //
        //        fishing = this.modelMapper.map(fishingServiceModel, Fishing.class);
    }
}
