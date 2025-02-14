package com.fk.facilities.dy.er.generator.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "puml")
@Data
@Configuration
public class PumlProperties {
    private String filename;
}
