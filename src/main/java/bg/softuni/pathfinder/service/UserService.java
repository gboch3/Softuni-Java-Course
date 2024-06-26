package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.data.UserRepository;
import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.UserLoginDTO;
import bg.softuni.pathfinder.model.dto.UserProfileDTO;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import jakarta.validation.constraints.Size;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    public boolean userExists(String username) {
        return this.userRepository.findByUsername(username) != null;
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        User user = this.modelMapper.map(userRegisterDTO, User.class);

        user.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));
        this.userRepository.save(user);
    }

    public void login(UserLoginDTO loginData) {
        User user = userRepository.findByUsername(loginData.getUsername());

        if (user == null) {
            // TODO throw error
            throw new RuntimeException("Username not found");
        }

        if (passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
            currentUser.setUser(user);
        }
    }

    public void logout() {
        currentUser.setUser(null);
    }

    public UserProfileDTO getProfileData() {
        return modelMapper.map(currentUser.getUser(), UserProfileDTO.class);
    }
}
