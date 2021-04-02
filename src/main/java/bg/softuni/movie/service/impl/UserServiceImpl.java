package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.UserRoleEntity;
import bg.softuni.movie.model.entity.enums.UserRoleEnum;
import bg.softuni.movie.model.service.UserDetailsServiceModel;
import bg.softuni.movie.model.service.UserRegisterServiceModel;
import bg.softuni.movie.repository.UserRepository;
import bg.softuni.movie.repository.UserRoleRepository;
import bg.softuni.movie.service.CloudinaryService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final MovieDBUserService movieDBUserService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, MovieDBUserService movieDBUserService, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.movieDBUserService = movieDBUserService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void seedUsers() {
        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);

            UserEntity admin = new UserEntity()
                    .setUsername("admin")
                    .setFullName("Silviya Yankova")
                    .setPassword(passwordEncoder.encode("11111"))
                    .setEmail("sss@sss.ss")
                    .setRoles(List.of(adminRole, userRole))
                    .setImageUrl("https://res.cloudinary.com/dpuujizet/image/upload/v1615840053/b56dvhio3kxbo10spz9b.png");

            UserEntity user = new UserEntity()
                    .setUsername("user1")
                    .setFullName("First User")
                    .setPassword(passwordEncoder.encode("11111"))
                    .setEmail("111@111.11")
                    .setRoles(List.of(userRole))
                    .setImageUrl("https://res.cloudinary.com/dpuujizet/image/upload/v1615840053/b56dvhio3kxbo10spz9b.png");


            userRepository.save(admin);
            userRepository.save(user);
        }
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) throws IOException {

        UserEntity newUser = modelMapper.map(userRegisterServiceModel, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
        newUser.setImageUrl("https://res.cloudinary.com/dpuujizet/image/upload/v1615840053/b56dvhio3kxbo10spz9b.png");

        UserRoleEntity userRole = userRoleRepository
                .findByRole(UserRoleEnum.USER)
                .orElseThrow(() -> new IllegalArgumentException("User role not found. Please seed the roles"));

        newUser.addRole(userRole);

        newUser = userRepository.save(newUser);

        UserDetails principal = movieDBUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
                newUser.getPassword(),
                principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean usernameAlreadyExists(String username) {
        return userRepository
                .findByUsername(username).isPresent();
    }

    @Override
    public boolean emailAlreadyExists(String username) {
        return userRepository
                .findByEmail(username).isPresent();
    }

    @Override
    public void addPicture(UserDetailsServiceModel userDetailsServiceModel, String username) throws IOException {
        MultipartFile img = userDetailsServiceModel.getImageUrl();
        String imageUrl = cloudinaryService.uploadImage(img);

        UserEntity user = userRepository.findByUsername(username).get();
        user.setImageUrl(imageUrl);

        userRepository.save(user);
    }

    @Override
    public UserEntity findUser(String username) {
        return userRepository
                .findByUsername(username)
                .get();
    }

}
