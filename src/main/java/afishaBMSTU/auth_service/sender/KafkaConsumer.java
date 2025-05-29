package afishaBMSTU.auth_service.sender;

import afishaBMSTU.auth_service.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final UserService userService;

    @KafkaListener(topics = "#{'${integration.kafka.topic.user-delete}'}")
    public void listen(UUID externalId) {
        if (externalId != null) {
            userService.deleteUser(externalId);
        }
    }
}
