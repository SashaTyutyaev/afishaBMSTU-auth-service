package afishaBMSTU.auth_service.security;

import afishaBMSTU.auth_lib.security.BaseJwtService;
import afishaBMSTU.auth_service.dto.JwtTokenDataDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@Slf4j
public class JwtService extends BaseJwtService<JwtTokenDataDto> {

    @Value("${security.jwt.secret}")
    private String secret;

    @Override
    protected Class<JwtTokenDataDto> getDataType() {
        return JwtTokenDataDto.class;
    }

    @Override
    public String generateToken(JwtTokenDataDto data) {
        return Jwts.builder()
                .setSubject("auth-user")
                .claim("data", data)
                .setIssuedAt(new Date())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}