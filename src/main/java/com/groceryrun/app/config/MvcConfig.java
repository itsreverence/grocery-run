package com.groceryrun.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for the MVC layer of the application
 */
@Configuration
class MvcConfig implements WebMvcConfigurer {

    /**
     * Adds view controllers for the application
     * @param registry ViewControllerRegistry object
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("landing");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/stores").setViewName("stores");
        registry.addViewController("/aisles/{id}").setViewName("aisle-detail");
        registry.addViewController("/categories/{id}").setViewName("category-detail");
        registry.addViewController("/grocery-lists/{id}").setViewName("grocery-list-detail");
        registry.addViewController("/account").setViewName("account");
        registry.addViewController("/users").setViewName("users");
    }

}
