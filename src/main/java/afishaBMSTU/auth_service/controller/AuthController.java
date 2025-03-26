package afishaBMSTU.auth_service.controller;

import afishaBMSTU.auth_service.dto.LoginRequestDto;
import afishaBMSTU.auth_service.dto.SignupRequestDto;
import afishaBMSTU.auth_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API для регистрации и аутентификации")
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Регистрация нового пользователя",
            description = "Создает нового пользователя в системе",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для регистрации",
                    required = true, content = @Content(schema = @Schema(implementation = SignupRequestDto.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегистрирован"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные регистрации"),
                    @ApiResponse(responseCode = "409", description = "Пользователь с таким email уже существует")
            }
    )
    @PostMapping("/sign-up")
    public void register(@RequestBody SignupRequestDto signupRequestDto, HttpServletRequest request) {
        authService.register(signupRequestDto, request);
    }

    @Operation(
            summary = "Аутентификация пользователя",
            description = "Выполняет вход пользователя и возвращает JWT токен",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для входа",
                    required = true, content = @Content(schema = @Schema(implementation = LoginRequestDto.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешная аутентификация",
                            content = @Content(schema = @Schema(
                                    implementation = String.class,
                                    description = "JWT токен",
                                    example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                            ))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Неверные учетные данные"
                    )
            }
    )
    @PostMapping("/sign-in")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }
}