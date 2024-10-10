package com.fk.facilities.dy.er.generator.config;

import com.fk.facilities.dy.er.generator.config.properties.VelocityProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class VelocityEngineConfig {

    @NonNull
    private final VelocityProperties velocityProperties;
    @Bean
    public VelocityEngine velocityEngine() {
        // Create properties for VelocityEngine
//        Properties props = new Properties();
//
//        // File loader setup
//        props.setProperty("resource.loader", "file");
//        props.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//
//        // Enable template caching
//        props.setProperty("file.resource.loader.cache", "true");
//
//        // Set check interval (in seconds) for file changes, set to '0' to disable
//        props.setProperty("file.resource.loader.modificationCheckInterval", "60");

        // Initialize VelocityEngine with the properties
        VelocityEngine velocityEngine = new VelocityEngine(velocityProperties.getProperties());
        velocityEngine.init();

        return velocityEngine;
    }

    @Bean
    public Template velocityTemplate() {
        return velocityEngine().getTemplate(velocityProperties.getTemplate());
    }
}
