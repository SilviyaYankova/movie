package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.UserRoleEntity;
import bg.softuni.movie.model.entity.enums.UserRoleEnum;
import bg.softuni.movie.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieUserServiceTest {

    private MovieUserService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    public void setUp() {
        serviceToTest = new MovieUserService(mockUserRepository);
    }

    @org.junit.jupiter.api.Test
    void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class, () -> {
                    serviceToTest.loadUserByUsername("user_does_not_exits");
                }
        );
    }

    @Test
    void testExistingUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("pesho");
        userEntity.setPassword("pesho");

        UserRoleEntity roleUser = new UserRoleEntity();
        roleUser.setRole(UserRoleEnum.USER);
        UserRoleEntity roleAdmin = new UserRoleEntity();
        roleAdmin.setRole(UserRoleEnum.ADMIN);

        userEntity.setRoles(List.of(roleUser, roleAdmin));

        when(mockUserRepository.findByUsername("11111")).
                thenReturn(Optional.of(userEntity));

        UserDetails userDetails = serviceToTest.loadUserByUsername("11111");

        Assertions.assertEquals(userEntity.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());

        List<String> authorities = userDetails.
                getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                collect(Collectors.toList());

        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
        Assertions.assertTrue(authorities.contains("ROLE_USER"));
    }
}
