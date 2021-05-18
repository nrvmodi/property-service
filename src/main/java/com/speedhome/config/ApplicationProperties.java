package com.speedhome.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
public class ApplicationProperties {

    @Getter
    @Setter
    private Jwt jwt = new Jwt();

    public class Jwt {

        @Getter
        @Setter
        private String secret;

        @Getter
        @Setter
        Long tokenValidityInSeconds;

    }

}
