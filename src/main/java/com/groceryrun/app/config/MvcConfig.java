package com.groceryrun.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("landing");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/stores").setViewName("stores");
        registry.addViewController("/aisles/{id}").setViewName("aisle-detail");
    }

}
