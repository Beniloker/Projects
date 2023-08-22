package com.example.jwtvega;

import com.example.jwtvega.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class JwtVegaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtVegaApplication.class, args);
    }

}
