package maa.myfishing.service.user;

import maa.myfishing.base.BaseTest;
import maa.myfishing.data.models.Role;
import maa.myfishing.data.models.User;
import maa.myfishing.data.models.UserInfo;
import maa.myfishing.data.reposipories.RoleRepository;
import maa.myfishing.data.reposipories.UserInfoRepository;
import maa.myfishing.data.reposipories.UserRepository;
import maa.myfishing.service.models.UserServiceModel;
import maa.myfishing.service.serices.RoleService;
import maa.myfishing.service.serices.UserInfoService;
import maa.myfishing.service.serices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImplBaseTest extends BaseTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    RoleRepository roleRepository;

    @MockBean
    UserInfoRepository userInfoRepository;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

    protected UserServiceModel getMockUserServiceModel() {
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername("Ivan");
        userServiceModel.setPassword("123456");
        userServiceModel.setEmail("user@user.com");

        return userServiceModel;
    }

    protected List<User> getMockUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setUsername("User1");
        user2.setUsername("User2");
        user2.setUsername("User3");
        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }

    protected User getMockUser() {
        User user = new User();
        user.setUsername("Ivan");
        user.setPassword("123456");
        user.setEmail("user@user.com");

        return user;
    }

    protected UserInfo getMockUserInfo() {
        User mockUser = getMockUser();
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(mockUser);
        userInfo.setId("UserInfoId");

        return userInfo;
    }

    protected Role getMockRole() {
        return new Role("ROLE_USER");
    }

}
