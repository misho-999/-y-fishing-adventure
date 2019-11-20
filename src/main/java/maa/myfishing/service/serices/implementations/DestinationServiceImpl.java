package maa.myfishing.service.serices.implementations;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.TypeOfOvernight;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.eroors.DestinationNotFoundException;
import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.serices.DestinationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        return this.modelMapper.map(this.destinationRepository.saveAndFlush(destination), DestinationServiceModel.class);

    }

    @Override
    public DestinationServiceModel getDestinationByTownName(String townName) {

        Destination destination = this.destinationRepository.findByTownName(townName)
                .orElseThrow(() -> new DestinationNotFoundException("Destination with the given id was not found!"));

        return this.modelMapper.map(destination, DestinationServiceModel.class);
    }

    @Override
    public List<DestinationServiceModel> getAllDestinations() {
        return this.destinationRepository.findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, DestinationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public DestinationServiceModel getDestinationById(String id) {
        return this.modelMapper.map(this.destinationRepository.getOne(id), DestinationServiceModel.class);
    }

    @Override
    public DestinationServiceModel editDestination(String id, DestinationServiceModel destinationServiceModel) {
        Destination destination = this.destinationRepository.findById(id)
                .orElseThrow(() -> new DestinationNotFoundException("Destination with the given id was not found!"));

        //   private String imgUrl;
        //    private String townName;
        //    private Integer population;
        //    private Integer altitude;
        //    private String description;

        destination.setImgUrl(destinationServiceModel.getImgUrl());
        destination.setTownName(destinationServiceModel.getTownName());
        destination.setPopulation(destinationServiceModel.getPopulation());
        destination.setAltitude(destinationServiceModel.getAltitude());
        destination.setDescription(destinationServiceModel.getDescription());

        return this.modelMapper.map(this.destinationRepository.saveAndFlush(destination), DestinationServiceModel.class);


//        Product product = this.productRepository.findById(id)
//                .orElseThrow(() -> new ProductNotFoundException("Product with the given id was not found!"));
//
//        productServiceModel.setCategories(
//                this.categoryService.findAllCategories()
//                        .stream()
//                        .filter(c -> productServiceModel.getCategories().contains(c.getId()))
//                        .collect(Collectors.toList())
//        );
//
//        product.setName(productServiceModel.getName());
//        product.setDescription(productServiceModel.getDescription());
//        product.setPrice(productServiceModel.getPrice());
//        product.setCategories(
//                productServiceModel.getCategories()
//                        .stream()
//                        .map(c -> this.modelMapper.map(c, Category.class))
//                        .collect(Collectors.toList())
//        );
//
//        this.offerRepository.findByProduct_Id(product.getId())
//                .ifPresent((o) -> {
//                    o.setPrice(product.getPrice().multiply(new BigDecimal(0.8)));
//
//                    this.offerRepository.save(o);
//                });
//
//        return this.modelMapper.map(this.productRepository.saveAndFlush(product), ProductServiceModel.class);

    }
}
