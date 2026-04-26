package com.groceryrun.app.security;

import com.groceryrun.app.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the Grocery Run application
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    /**
     * Configures the security filter chain
     * @param http HttpSecurity object
     * @return SecurityFilterChain object
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        // @formatter:off
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/stores", "/dashboard", "/aisles/**", "/categories/**", "/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/stores/admin/**", "/api/v1/aisles/admin/**", "/api/v1/categories/admin/**", "/api/v1/users/admin/**", "/api/v1/items/admin/**", "/api/v1/locations/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
        // @formatter:on

        return http.build();
    }

    /**
     * Configures the password encoder
     * @return PasswordEncoder object
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the user details service
     * @param userRepository repository for user data
     * @return UserDetailsService object
     */
    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            com.groceryrun.app.entities.User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
            return User.builder().username(appUser.getUsername()).password(appUser.getPasswordHash()).roles(appUser.getRole().name()).build();
        };
    }
}