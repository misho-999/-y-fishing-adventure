package maa.myfishing.service.user;

import maa.myfishing.data.models.Destination;
import maa.myfishing.data.models.Role;
import maa.myfishing.data.models.User;
import maa.myfishing.data.models.UserInfo;
import maa.myfishing.service.models.UserServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceImplTest extends UserServiceImplBaseTest {

    @Test
    void registerUserWhenUserIsFirstRegistered_ShouldToRegister() {
        UserServiceModel mockUserServiceModel = getMockUserServiceModel();
        User mockUser = getMockUser();
        UserInfo mockUserInfo = getMockUserInfo();

        Mockito.when(userRepository.count()).thenReturn(0L);
        Mockito.when(userRepository.saveAndFlush(any(User.class))).thenReturn(mockUser);
        Mockito.when(userInfoRepository.saveAndFlush(any(UserInfo.class))).thenReturn(mockUserInfo);

        UserServiceModel userServiceModel = userService.registerUser(mockUserServiceModel);

        assertNotNull(userServiceModel);
        assertEquals(mockUser.getUsername(), userServiceModel.getUsername());
    }

    @Test
    void registerUserWhenUserIsNotFirstRegistered_ShouldToRegister() {
        String authority = "ROLE_USER";
        UserServiceModel mockUserServiceModel = getMockUserServiceModel();
        User mockUser = getMockUser();
        UserInfo mockUserInfo = getMockUserInfo();
        Role mockRole = getMockRole();

        Mockito.when(userRepository.count()).thenReturn(2L);
        Mockito.when(userRepository.saveAndFlush(any(User.class))).thenReturn(mockUser);
        Mockito.when(userInfoRepository.saveAndFlush(any(UserInfo.class))).thenReturn(mockUserInfo);
        Mockito.when(roleRepository.findByAuthority(authority)).thenReturn(mockRole);

        UserServiceModel userServiceModel = userService.registerUser(mockUserServiceModel);

        assertNotNull(userServiceModel);
        assertEquals(mockUser.getUsername(), userServiceModel.getUsername());
    }

    @Test
    void loadUserByUsername_WhenUserIsPresent_ShouldReturnUser() {
        String username = "Ivan";
        User mockUser = getMockUser();

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        UserDetails userDetails = userService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
    }

    @Test
    void loadUserByUsername_WhenUserIsNotPresent_ShouldThrowException() {
        String username = "Ivan";

        assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername(username));
    }

    @Test
    void findUserByUserName_WhenUserIsPresent_ShouldReturnUser() {
        String username = "Ivan";
        User mockUser = getMockUser();

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        UserServiceModel mockUserService = userService.findUserByUserName(username);

        assertEquals(username, mockUser.getUsername());
    }

    @Test
    void findUserByUserName_WhenUserIsNotPresent_ShouldThrowException() {
        String username = "Ivan";

        assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername(username));
    }

//    @Test
//    void editUserProfile_WhenUserIsPresent_ShouldEditUser() {
//        String username = "Ivan";
//        String oldPassword = "123456";
//        User mockUser = getMockUser();
//        UserServiceModel mockUserServiceModel = getMockUserServiceModel();
//        mockUserServiceModel.setUsername("EditedUsername");
//
//        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
//        Mockito.when(userRepository.saveAndFlush(any(User.class))).thenReturn(mockUser);
//        Mockito.when(bCryptPasswordEncoder.matches(oldPassword, mockUserServiceModel.getPassword())).thenReturn(true);
//
//        UserServiceModel editedUserServiceModel = userService.editUserProfile(mockUserServiceModel, oldPassword);
//
//        assertEquals("EditedUsername", editedUserServiceModel.getUsername());
//    }

    @Test
    void getAllUsers_WhenUsersAreNotPresent_ShouldReturnEmptyList() {
        List<UserServiceModel> allMockUsers = userService.getAllUsers();

        assertEquals(0, allMockUsers.size());
    }

    @Test
    void getAllUsers_WhenUsersArePresent_ShouldReturnUsers() {
        List<User> mockUsers = getMockUsers();

        Mockito.when(userRepository.findAll()).thenReturn(mockUsers);

        List<UserServiceModel> allMockUsers = userService.getAllUsers();

        assertEquals(3, allMockUsers.size());
        assertEquals("User1", allMockUsers.get(0).getUsername());
    }

//    @Test
//    void setUserRole() {
//        String id = "id";
//        String role = "admin";
//        User user = getMockUser();
//
//        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
//        Mockito.when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);
//
//        userService.setUserRole(id, role);
//    }
}