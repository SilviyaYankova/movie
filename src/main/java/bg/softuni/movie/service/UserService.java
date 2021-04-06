package bg.softuni.movie.service;

import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.UserServiceModel;
import bg.softuni.movie.model.service.UserRegisterServiceModel;
import bg.softuni.movie.model.view.UserViewModel;

import java.io.IOException;
import java.util.List;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) throws IOException;

    boolean usernameAlreadyExists(String username);

    boolean emailAlreadyExists(String username);

    void addPicture(UserServiceModel userServiceModel, String username) throws IOException;

    UserEntity findUser(String username);

    List<UserViewModel> getAllUsers();

    void grantAuthority(Long userId, String newRole);

    UserEntity findUserById(Long userId);
}
