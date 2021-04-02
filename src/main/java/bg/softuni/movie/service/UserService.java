package bg.softuni.movie.service;

import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.UserDetailsServiceModel;
import bg.softuni.movie.model.service.UserRegisterServiceModel;

import java.io.IOException;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) throws IOException;

    boolean usernameAlreadyExists(String username);

    boolean emailAlreadyExists(String username);

    void addPicture(UserDetailsServiceModel userDetailsServiceModel, String username) throws IOException;

    UserEntity findUser(String username);
}
