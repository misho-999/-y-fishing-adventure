package maa.myfishing.service.serices;

import maa.myfishing.service.models.DestinationServiceModel;
import maa.myfishing.service.models.UserInfoServiceModel;
import maa.myfishing.service.models.UserServiceModel;

public interface UserInfoService {

    UserInfoServiceModel saveUserInfo(UserInfoServiceModel userInfoServiceModel);

    void addDestination(String townName, String username);
}
