package br.com.estudo.alurachallengebackendsemana1.utils.security;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            var encoder = Algorithm.HMAC256(secret);

            return JWT.create().withIssuer("API Challenge Alura 1")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDate())
                    .sign(encoder);
        } catch (JWTCreationException e){
            throw new RuntimeException("Error generating jwt token", e);
        }
    }

    public String getSubject(String tokenJWT){
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("API Challenge Alura 1")
                    .build().verify(tokenJWT).getSubject();
        } catch (JWTVerificationException e){
            throw new RuntimeException("Invalid or expired JWT token!");
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).
                toInstant(ZoneOffset.of("-03:00"));
    }
}
