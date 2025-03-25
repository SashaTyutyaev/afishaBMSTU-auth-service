package afishaBMSTU.auth_service.security;

import afishaBMSTU.auth_lib.security.BaseJwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class JwtService extends BaseJwtService<UUID> {

    @Value("${security.jwt.secret}")
    private String secret;

    @Override
    protected Class<UUID> getDataType() {
        return UUID.class;
    }

    @Override
    public String generateToken(UUID data, List<String> roles) {
        return Jwts.builder()
                .setSubject("auth-user")
                .claim("data", data)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}