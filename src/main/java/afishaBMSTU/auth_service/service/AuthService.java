package afishaBMSTU.auth_service.service;

import afishaBMSTU.auth_service.dto.LoginRequestDto;
import afishaBMSTU.auth_service.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    void register(SignupRequestDto signupRequestDto, HttpServletRequest request);
    String login(LoginRequestDto loginRequestDto);

}
