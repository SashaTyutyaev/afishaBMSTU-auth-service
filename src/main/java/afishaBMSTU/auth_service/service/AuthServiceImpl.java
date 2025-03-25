package afishaBMSTU.auth_service.service;

import afishaBMSTU.auth_service.client.UserServiceFeignClient;
import afishaBMSTU.auth_service.dto.LoginRequestDto;
import afishaBMSTU.auth_service.dto.SignupRequestDto;
import afishaBMSTU.auth_service.dto.UserCreationRequestDto;
import afishaBMSTU.auth_service.exception.IncorrectParameterException;
import afishaBMSTU.auth_service.exception.NotFoundException;
import afishaBMSTU.auth_service.mapper.UserMapper;
import afishaBMSTU.auth_service.model.Role;
import afishaBMSTU.auth_service.model.Roles;
import afishaBMSTU.auth_service.model.User;
import afishaBMSTU.auth_service.repository.RoleRepository;
import afishaBMSTU.auth_service.repository.UserRepository;
import afishaBMSTU.auth_service.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserServiceFeignClient userServiceFeignClient;

    @Override
    @Transactional
    public void register(SignupRequestDto signupRequestDto) {

        if (userRepository.existsByLogin(signupRequestDto.getLogin())) {
            log.error("Login is already using");
            throw new IncorrectParameterException("Login is already using");
        }

        User user = userMapper.toUser(signupRequestDto);

        user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        Role role = roleRepository.findByUserRole(Roles.ROLE_ADMIN);
        user.setRoles(Set.of(role));

        UserCreationRequestDto userCreationRequestDto = userMapper.toUserCreationRequest(signupRequestDto);
        userCreationRequestDto.setExternalId(user.getExternalId());
        userServiceFeignClient.registerUser(userCreationRequestDto);

        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public String login(LoginRequestDto loginRequestDto) {

        String login = loginRequestDto.getLogin();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, loginRequestDto.getPassword()));

        User user = getUserByLogin(login);
        List<String> roles = user.getRoles().stream()
                .map(role -> role.getUserRole().name())
                .toList();

        String token = jwtService.generateToken(user.getExternalId(), roles);
        log.info("Successfully authenticated and created token");
        return token;
    }

    private User getUserByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> {
            log.error("User with login not found");
            return new NotFoundException("User with login not found");
        });
    }
}
