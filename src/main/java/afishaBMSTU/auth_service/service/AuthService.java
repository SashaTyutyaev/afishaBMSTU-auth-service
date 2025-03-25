package afishaBMSTU.auth_service.service;

import afishaBMSTU.auth_service.dto.LoginRequestDto;
import afishaBMSTU.auth_service.dto.SignupRequestDto;

public interface AuthService {
    void register(SignupRequestDto signupRequestDto);
    String login(LoginRequestDto loginRequestDto);

}
