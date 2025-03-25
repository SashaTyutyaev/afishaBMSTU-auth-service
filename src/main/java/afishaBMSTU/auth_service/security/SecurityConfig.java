package afishaBMSTU.auth_service.security;

import afishaBMSTU.auth_lib.security.BaseAuthTokenFilter;
import afishaBMSTU.auth_lib.security.BaseSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig extends BaseSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(BaseAuthTokenFilter<?> authTokenFilter, CustomUserDetailsService customUserDetailsService) {
        super(authTokenFilter);
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(List.of(provider));
    }

    @Override
    protected void configureHttpSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/sign-in").permitAll()
                .requestMatchers("/api/auth/sign-up").permitAll()
                .requestMatchers("/v3/api-docs").permitAll()
        );

    }
}
