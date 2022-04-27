package com.company.common.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private final JwtProperties jwtProperties = new JwtProperties();

    @Getter
    @Setter
    public static class JwtProperties {
        private String secret;
        private long tokenValidityInSeconds;
        private long tokenValidityInSecondsForRememberMe;
    }

}
