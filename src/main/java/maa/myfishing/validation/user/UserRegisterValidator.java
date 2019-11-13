package maa.myfishing.validation.user;

import maa.myfishing.data.reposipories.UserRepository;
import maa.myfishing.validation.ValidationConstants;
import maa.myfishing.validation.annotation.Validator;
import maa.myfishing.web.models.UserRegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class UserRegisterValidator implements org.springframework.validation.Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterModel userRegisterModel = (UserRegisterModel) o;

        if (this.userRepository.findByUsername(userRegisterModel.getUsername()).isPresent()) {
            errors.rejectValue("username",
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, userRegisterModel.getUsername()),
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, userRegisterModel.getUsername()));
        }

        if (userRegisterModel.getUsername().length() < 3 || userRegisterModel.getUsername().length() > 10) {
            errors.rejectValue(
                    "username",
                    ValidationConstants.USERNAME_LENGTH,
                    ValidationConstants.USERNAME_LENGTH
            );
        }

        if (!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            errors.rejectValue("password", ValidationConstants.PASSWORDS_DO_NOT_MATCH,
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH);
        }

        if (this.userRepository.findByEmail(userRegisterModel.getEmail()).isPresent()) {
            errors.rejectValue("email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterModel.getEmail()));
        }
    }
}
