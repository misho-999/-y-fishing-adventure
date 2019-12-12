package maa.myfishing.service.userInfo;

import maa.myfishing.base.BaseTest;
import maa.myfishing.data.models.User;
import maa.myfishing.data.models.UserInfo;
import maa.myfishing.data.reposipories.UserInfoRepository;
import maa.myfishing.service.serices.UserInfoService;
import maa.myfishing.service.serices.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoServiceImplTest extends BaseTest {
    @MockBean
    UserInfoRepository userInfoRepository;

    @Autowired
    UserInfoService userInfoService;

    @Test
    void getUserByUsername() {
        String username = "Ivan";
        User user = new User();
        user.setUsername(username);

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);

        Mockito.when(userInfoRepository.findByUserUsername(username)).thenReturn(Optional.of(userInfo));

        UserInfo mockUserInfo = userInfoService.getUserByUsername(username);

        assertEquals("Ivan", mockUserInfo.getUser().getUsername());
    }

    //  public UserInfo getUserByUsername(String username) {
    //        return this.userInfoRepository.findByUserUsername(username)
    //                .orElseThrow(() -> new UserNotFoundException(String.format(UserValidationConstants.USER_NOT_FOUND_EXCEPTION, username)));
    //    }
}