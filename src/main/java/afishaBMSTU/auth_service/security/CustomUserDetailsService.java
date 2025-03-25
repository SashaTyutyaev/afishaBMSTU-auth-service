package afishaBMSTU.auth_service.security;

import afishaBMSTU.auth_service.exception.NotFoundException;
import afishaBMSTU.auth_service.model.CustomUserDetails;
import afishaBMSTU.auth_service.model.User;
import afishaBMSTU.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login).orElseThrow(() -> {
            log.error("User is not found");
            return new NotFoundException("User is not found");
        });

        return new CustomUserDetails(login, user.getExternalId(), user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getUserRole().name()))
                        .toList());
    }
}
