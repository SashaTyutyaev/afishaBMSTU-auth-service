package afishaBMSTU.auth_service.controller;

import afishaBMSTU.auth_service.dto.LoginRequestDto;
import afishaBMSTU.auth_service.dto.SignupRequestDto;
import afishaBMSTU.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public void register(SignupRequestDto signupRequestDto) {
        authService.register(signupRequestDto);
    }

    @PostMapping("/sign-in")
    public String login(LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }
}
