package afishaBMSTU.auth_service.client;

import afishaBMSTU.auth_service.dto.UserCreationRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${integration.rest.user-service.url}")
public interface UserServiceFeignClient {

    @PostMapping("/api/users/register")
    void registerUser(@RequestBody UserCreationRequestDto requestDto);
}
