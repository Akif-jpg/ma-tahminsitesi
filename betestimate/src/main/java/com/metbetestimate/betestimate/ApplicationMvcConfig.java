package com.metbetestimate.betestimate;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ApplicationMvcConfig implements WebMvcConfigurer{

    @Override
    public String toString() {
        return "ApplicationMvcConfig []";
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:///images");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
    
}
