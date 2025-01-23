package com.example.library.configuration.security.jwt.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTEnvironment {
    private final String JWT_SECRET_TOKEN;
    private final int JWT_LIFETIME;


    public JWTEnvironment(@Value("${jwt.secret}") String JWT_SECRET_TOKEN, @Value("${jwt.lifetime}") int JWT_LIFETIME) {
        this.JWT_SECRET_TOKEN = JWT_SECRET_TOKEN;
        this.JWT_LIFETIME = JWT_LIFETIME;
    }

    public String getJWT_SECRET_TOKEN() {
        return JWT_SECRET_TOKEN;
    }

    public int getJWT_LIFETIME() {
        return JWT_LIFETIME;
    }
}
