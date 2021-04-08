package bg.softuni.movie.service.impl;

import bg.softuni.movie.exceptions.ObjectNotFoundException;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.UserRoleEntity;
import bg.softuni.movie.model.entity.enums.UserRoleEnum;
import bg.softuni.movie.model.service.UserServiceModel;
import bg.softuni.movie.model.service.UserRegisterServiceModel;
import bg.softuni.movie.model.view.UserViewModel;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final MovieUserService movieUserService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, MovieUserService movieUserService, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.movieUserService = movieUserService;
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
                    .setImageUrl("https://res.cloudinary.com/dpuujizet/image/upload/v1615840053/b56dvhio3kxbo10spz9b.png")
                    .setRegisteredOn(LocalDate.now());

            UserEntity user = new UserEntity()
                    .setUsername("user1")
                    .setFullName("First User")
                    .setPassword(passwordEncoder.encode("11111"))
                    .setEmail("111@111.11")
                    .setRoles(List.of(userRole))
                    .setImageUrl("https://res.cloudinary.com/dpuujizet/image/upload/v1615840053/b56dvhio3kxbo10spz9b.png")
                    .setRegisteredOn(LocalDate.now());


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
        newUser.setRegisteredOn(LocalDate.now());

        newUser = userRepository.save(newUser);

        UserDetails principal = movieUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
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
    public void addPicture(UserServiceModel userServiceModel, String username) throws IOException {
        MultipartFile img = userServiceModel.getImageUrl();
        String imageUrl = cloudinaryService.uploadImage(img);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(ObjectNotFoundException::new);

        user.setImageUrl(imageUrl);

        userRepository.save(user);
    }

    @Override
    public UserEntity findUser(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        List<UserViewModel> allUsers =  userRepository
                .findAll()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, UserViewModel.class))
                .collect(Collectors.toList());

        allUsers.remove(0);

        return allUsers;
    }

    @Override
    public void grantAuthority(Long userId, String newRole) {

        newRole = newRole.toUpperCase();

        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(ObjectNotFoundException::new);

        UserRoleEntity adminRole = userRoleRepository
                .findByRole(UserRoleEnum.ADMIN)
                .orElseThrow(ObjectNotFoundException::new);

        UserRoleEntity userRole = userRoleRepository
                .findByRole(UserRoleEnum.USER)
                .orElseThrow(ObjectNotFoundException::new);


        String admin = adminRole.getRole().name();
        String user = userRole.getRole().name();

        if (admin.equals(newRole)) {
            userEntity.setRoles(List.of(adminRole, userRole));
        } else {
            userEntity.setRoles(List.of(userRole));
        }

        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(ObjectNotFoundException::new);
    }
}
