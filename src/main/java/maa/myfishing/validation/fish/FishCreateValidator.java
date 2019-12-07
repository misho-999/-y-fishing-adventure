package maa.myfishing.validation.fish;


import maa.myfishing.constants.validation.FishValidationConstants;
import maa.myfishing.data.reposipories.FishRepository;
import maa.myfishing.validation.annotation.Validator;
import maa.myfishing.web.models.fish.FishCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class FishCreateValidator implements org.springframework.validation.Validator {
    private final FishRepository fishRepository;

    @Autowired
    public FishCreateValidator(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FishCreateModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FishCreateModel fishCreateModel = (FishCreateModel) o;

        if (this.fishRepository.findByFishNameAndWeightInKilogramsAndLengthInCentimeters(
                fishCreateModel.getFishName(), fishCreateModel.getWeightInKilograms()
                , fishCreateModel.getLengthInCentimeters()).isPresent()) {
            errors.rejectValue("fishName",
                    String.format(FishValidationConstants.FISH_ALREADY_EXIST_EXCEPTION, fishCreateModel.getFishName()),
                    String.format(FishValidationConstants.FISH_ALREADY_EXIST_EXCEPTION, fishCreateModel.getFishName()));
        }

        if (fishCreateModel.getFishName().length() < 3 || fishCreateModel.getFishName().length() > 20) {
            errors.rejectValue(
                    "fishName",
                    FishValidationConstants.FISH_NAME_LENGTH_EXCEPTION,
                    FishValidationConstants.FISH_NAME_LENGTH_EXCEPTION
            );
        }

        if (fishCreateModel.getWeightInKilograms() < 0.300 || fishCreateModel.getWeightInKilograms() > 100) {
            errors.rejectValue(
                    "weightInKilograms",
                    FishValidationConstants.FISH_WEIGHT_EXCEPTION,
                    FishValidationConstants.FISH_WEIGHT_EXCEPTION
            );
        }

        if (fishCreateModel.getLengthInCentimeters() < 20 || fishCreateModel.getLengthInCentimeters() > 300) {
            errors.rejectValue(
                    "lengthInCentimeters",
                    FishValidationConstants.FISH_LENGTH_EXCEPTION,
                    FishValidationConstants.FISH_LENGTH_EXCEPTION
            );
        }

    }
}
