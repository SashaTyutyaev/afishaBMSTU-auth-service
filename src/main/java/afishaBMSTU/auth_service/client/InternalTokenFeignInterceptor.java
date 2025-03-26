package afishaBMSTU.auth_service.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
@Slf4j
public class InternalTokenFeignInterceptor implements RequestInterceptor {

    @Value("${integration.rest.user-service.token}")
    private String internalToken;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization",
                new String(Base64.getDecoder().decode(internalToken), StandardCharsets.UTF_8).trim());
    }
}
