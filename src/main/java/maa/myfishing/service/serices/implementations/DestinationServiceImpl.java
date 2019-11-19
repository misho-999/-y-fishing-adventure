package maa.myfishing.service.serices.implementations;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.TypeOfOvernight;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.serices.DestinationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository, ModelMapper modelMapper) {
        this.destinationRepository = destinationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DestinationServiceModel addDestination(DestinationServiceModel model) {
        Destination destination = this.modelMapper.map(model, Destination.class);

        destination.setTypeOfOvernight(TypeOfOvernight.valueOf((model.getTypeOfOvernight()).toUpperCase()));

        return this.modelMapper.map(this.destinationRepository.saveAndFlush(destination), DestinationServiceModel.class);

    }

    @Override
    public DestinationServiceModel getDestinationByTownName(String townName) {

        Destination destination = this.destinationRepository.findByTownName(townName);

        return this.modelMapper.map(destination, DestinationServiceModel.class);
    }

    @Override
    public List<DestinationServiceModel> getAllDestinations() {
        return this.destinationRepository.findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, DestinationServiceModel.class))
                .collect(Collectors.toList());
    }
}
