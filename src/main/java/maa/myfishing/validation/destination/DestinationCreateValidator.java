package maa.myfishing.validation.destination;

import maa.myfishing.constants.validation.DestinationValidationConstants;
import maa.myfishing.data.reposipories.DestinationRepository;
import maa.myfishing.validation.annotation.Validator;
import maa.myfishing.web.models.destination.DestinationCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class DestinationCreateValidator implements org.springframework.validation.Validator {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationCreateValidator(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return DestinationCreateModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DestinationCreateModel destinationCreateModel = (DestinationCreateModel) o;

        if (this.destinationRepository.findByTownName(destinationCreateModel.getTownName()).isPresent()) {
            errors.rejectValue("townName",
                    String.format(DestinationValidationConstants.DESTINATION_ALREADY_EXIST_EXCEPTION, destinationCreateModel.getTownName()),
                    String.format(DestinationValidationConstants.DESTINATION_ALREADY_EXIST_EXCEPTION, destinationCreateModel.getTownName()));
        }

        if (destinationCreateModel.getTownName().length() < 3 || destinationCreateModel.getTownName().length() > 30) {
            errors.rejectValue(
                    "townName",
                    DestinationValidationConstants.DESTINATION_NAME_LENGTH,
                    DestinationValidationConstants.DESTINATION_NAME_LENGTH
            );
        }

        if (destinationCreateModel.getAltitude() < 0 || destinationCreateModel.getAltitude() > 5000000) {
            errors.rejectValue(
                    "population",
                    DestinationValidationConstants.DESTINATION_POPULATION_EXCEPTION,
                    DestinationValidationConstants.DESTINATION_POPULATION_EXCEPTION
            );
        }

        if (destinationCreateModel.getAltitude() < 3 || destinationCreateModel.getAltitude() > 5000) {
            errors.rejectValue(
                    "altitude",
                    DestinationValidationConstants.DESTINATION_ALTITUDE_EXCEPTION,
                    DestinationValidationConstants.DESTINATION_ALTITUDE_EXCEPTION
            );
        }
    }
}
