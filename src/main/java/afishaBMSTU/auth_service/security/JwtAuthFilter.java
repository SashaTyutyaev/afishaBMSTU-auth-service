package afishaBMSTU.auth_service.security;

import afishaBMSTU.auth_lib.security.BaseAuthTokenFilter;
import afishaBMSTU.auth_service.dto.JwtTokenDataDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JwtAuthFilter extends BaseAuthTokenFilter<JwtTokenDataDto> {

    private final JwtService jwtService;

    @Override
    protected List<String> parseRoles(JwtTokenDataDto userInfo) {
        return userInfo.getRoles();
    }

    @Override
    protected JwtTokenDataDto retrieveUserInfo(String token) {
        return jwtService.extractUserInfo(token);
    }
}
