package afishaBMSTU.auth_service.service.user;

import afishaBMSTU.auth_service.exception.NotFoundException;
import afishaBMSTU.auth_service.model.User;
import afishaBMSTU.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void deleteUser(UUID externalId) {
        User user = getUserByExternalId(externalId);
        userRepository.delete(user);
        log.info("User successfully deleted");
    }

    private User getUserByExternalId(UUID externalId) {
        return userRepository.findByExternalId(externalId).orElseThrow(() -> {
            log.error("User with externalId {} not found", externalId);
            return new NotFoundException("User for delete not found");
        });
    }
}
