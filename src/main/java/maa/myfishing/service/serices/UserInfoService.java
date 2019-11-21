package maa.myfishing.service.serices;

import maa.myfishing.service.models.UserInfoServiceModel;

public interface UserInfoService {

    UserInfoServiceModel saveUserInfo(UserInfoServiceModel userInfoServiceModel);

    void addDestination(String townName, String username);

}
