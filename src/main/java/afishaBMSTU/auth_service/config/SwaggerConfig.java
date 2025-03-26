package afishaBMSTU.auth_service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization"))
                        .addSchemas("JWTToken", new StringSchema()
                                .example("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
                                .description("JWT токен авторизации")))
                .info(new Info()
                        .title("Auth Service API")
                        .version("1.0")
                        .description("API для аутентификации и регистрации пользователей")
                        .contact(new Contact()
                                .name("Александр")
                                .url("https://t.me/s1lweak")));
    }
}
