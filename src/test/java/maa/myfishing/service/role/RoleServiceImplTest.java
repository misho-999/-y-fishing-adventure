package maa.myfishing.service.role;

import maa.myfishing.base.BaseTest;
import maa.myfishing.data.models.Role;
import maa.myfishing.data.reposipories.RoleRepository;
import maa.myfishing.service.models.RoleServiceModel;
import maa.myfishing.service.serices.RoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class RoleServiceImplTest extends BaseTest {

    @MockBean
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Test
    void seedRolesInDb() {
        Role role = getMockRole();

        Mockito.when(roleRepository.count()).thenReturn(0L);
        Mockito.when(roleRepository.saveAndFlush(any(Role.class))).thenReturn(role);

        roleService.seedRolesInDb();

        Role mockRole = roleRepository.saveAndFlush(role);

        assertNotNull(mockRole);
        assertEquals("ROLE_USER", mockRole.getAuthority());
    }

    @Test
    void findAllRoles_WhenHaveRegisteredUser_ShouldReturnRoles() {
        List<Role> allMockRoles = getAllMockRoles();

        Mockito.when(roleRepository.findAll()).thenReturn(allMockRoles);

        Set<RoleServiceModel> allRoles = roleService.findAllRoles();

        assertEquals(4, allRoles.size());

    }

    @Test
    void findAllRoles_WhenHaveNotRegisteredUser_ShouldReturnEmptyList() {
        Set<RoleServiceModel> allRoles = roleService.findAllRoles();

        assertEquals(0, allRoles.size());

    }

    @Test
    void findByAuthority_WhenHaveRegisteredUser_ShouldReturnAuthority() {
        String authority = "ROLE_USER";

        Role mockRole = getMockRole();

        Mockito.when(roleRepository.findByAuthority(authority)).thenReturn(mockRole);

        RoleServiceModel role = roleService.findByAuthority(authority);

        assertEquals( "ROLE_USER", role.getAuthority());



    }
    //  public RoleServiceModel findByAuthority(String authority) {
    //        return this.modelMapper.map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
    //    }

    private Role getMockRole() {
        return new Role("ROLE_USER");
    }

    private List<Role> getAllMockRoles() {
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_MODERATOR");
        Role role3 = new Role("ROLE_ADMIN");
        Role role4 = new Role("ROLE_ROOT");

        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        roles.add(role4);

        return roles;
    }
}