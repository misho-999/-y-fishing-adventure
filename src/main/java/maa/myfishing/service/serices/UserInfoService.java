package maa.myfishing.service.serices;

import maa.myfishing.data.models.UserInfo;
import maa.myfishing.service.models.UserInfoServiceModel;

public interface UserInfoService {

    UserInfoServiceModel saveUserInfo(UserInfoServiceModel userInfoServiceModel);

    void addDestinationToMyDestinatoins(String townName, String username);

    UserInfo getUserByUsername(String username);
}
