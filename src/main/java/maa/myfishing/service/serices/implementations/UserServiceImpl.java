//package maa.myfishing.service.serices.implementations;
//
//import maa.myfishing.service.models.UserServiceModel;
//import maa.myfishing.service.serices.UserService;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedHashSet;
//
//@Service
//public class UserServiceImpl implements UserService {
//    @Override
//    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
//        this.roleService.seedRolesInDb();
//        if (this.userRepository.count() == 0) {
//            userServiceModel.setAuthorities(this.roleService.findAllRoles());
//        } else {
//            userServiceModel.setAuthorities(new LinkedHashSet<>());
//
//            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
//        }
//
//
//        User user = this.modelMapper.map(userServiceModel, User.class);
//        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
//
//        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
//    }
//}
