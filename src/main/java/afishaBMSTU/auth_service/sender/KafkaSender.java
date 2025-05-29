package afishaBMSTU.auth_service.sender;

import afishaBMSTU.auth_service.dto.UserCreationRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaSender {

    @Value("${integration.kafka.topic.user-creation}")
    private String userCreationTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(UserCreationRequestDto userCreationRequestDto) {
        kafkaTemplate.send(userCreationTopic, userCreationRequestDto);
        log.info("Successfully sent user creation request: {}", userCreationRequestDto);
    }
}
