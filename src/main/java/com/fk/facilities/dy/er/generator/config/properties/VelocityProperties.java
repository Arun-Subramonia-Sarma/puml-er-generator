package com.fk.facilities.dy.er.generator.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Properties;


@ConfigurationProperties(prefix = "velocity")
@Data
@Configuration
public class VelocityProperties {
    private String template;
    private Properties properties;
}
