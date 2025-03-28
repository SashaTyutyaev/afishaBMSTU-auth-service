package afishaBMSTU.auth_service.client;

import afishaBMSTU.auth_service.dto.UserCreationRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AFI-BMSTU-user-service")
public interface UserServiceFeignClient {

    @PostMapping("/api/internal/create-user-after-sign-up")
    void registerUser(@RequestBody UserCreationRequestDto requestDto);
}
