package com.speedhome;

import com.speedhome.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})

public class SpeedhomePropertyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedhomePropertyServiceApplication.class, args);
    }

}
