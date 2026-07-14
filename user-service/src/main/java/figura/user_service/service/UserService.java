package figura.user_service.service;

import figura.user_service.dto.UserDto;
import figura.user_service.entity.User;
import figura.user_service.exception.UserNotFoundException;
import figura.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto input) {
        final User createdUser = User.builder()
            .name(input.name())
            .surname(input.surname())
            .email(input.email())
            .address(input.address())
            .alerting(input.alerting())
            .energyAlertingThreshold(input.energyAlertingThreshold())
            .build();

        final User saved = userRepository.save(createdUser);

        return toDto(saved);
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
            .map(this::toDto).orElseThrow(() ->
                new UserNotFoundException("User not found with id " + id));
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setName(userDto.name());
        user.setSurname(userDto.surname());
        user.setEmail(userDto.email());
        user.setAddress(userDto.address());
        user.setAlerting(userDto.alerting());
        user.setEnergyAlertingThreshold(userDto.energyAlertingThreshold());

        final User updatedUser = userRepository.save(user);

        return toDto(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    private UserDto toDto(User user) {
        return new UserDto(
            user.getId(),
            user.getName(),
            user.getSurname(),
            user.getEmail(),
            user.getAddress(),
            user.isAlerting(),
            user.getEnergyAlertingThreshold());
    }
}
