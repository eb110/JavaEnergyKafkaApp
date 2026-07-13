package figura.user_service.service;

import figura.user_service.dto.UserDto;
import figura.user_service.entity.User;
import figura.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto input) {
        log.info("UserService:createUser: {}", input);
        final User createdUser = User.builder()
            .name(input.getName())
            .surname(input.getSurname())
            .email(input.getEmail())
            .address(input.getAddress())
            .alerting(input.isAlerting())
            .energyAlertingThreshold(input.getEnergyAlertingThreshold())
            .build();

        final User saved = userRepository.save(createdUser);

        return toDto(saved);
    }

    public UserDto getUserById(Long id) {
        log.info("UserService:GetUserById: {}", id);
        return userRepository.findById(id)
            .map(this::toDto).orElse(null);
    }

    public void updateUser(Long id, UserDto userDto) {
        log.info("UserService:updateUser: {}", userDto);
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setAlerting(userDto.isAlerting());
        user.setEnergyAlertingThreshold(userDto.getEnergyAlertingThreshold());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        log.info("UserService:DeleteUser: {}", id);
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
            .id(user.getId())
            .name(user.getName())
            .surname(user.getSurname())
            .email(user.getEmail())
            .address(user.getAddress())
            .alerting(user.isAlerting())
            .energyAlertingThreshold(user.getEnergyAlertingThreshold())
            .build();
    }
}
