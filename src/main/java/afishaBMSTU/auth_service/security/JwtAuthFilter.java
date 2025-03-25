package afishaBMSTU.auth_service.security;

import afishaBMSTU.auth_lib.security.BaseAuthTokenFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JwtAuthFilter extends BaseAuthTokenFilter {
    @Override
    protected List<String> parseRoles(Object userInfo) {
        return null;
    }

    @Override
    protected Object retrieveUserInfo(String token) {
        return null;
    }

    @Override
    protected boolean shouldSkipFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.contains("/swagger-ui/") ||
                requestURI.contains("/v3/api-docs") ||
                requestURI.contains("/api/auth") ||
                shouldSkipFilterAddons(requestURI);
    }
}
