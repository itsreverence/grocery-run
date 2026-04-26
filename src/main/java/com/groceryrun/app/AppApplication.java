package com.groceryrun.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Grocery Run application
 */
@SpringBootApplication
public class AppApplication {
    /**
     * Main method for the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
