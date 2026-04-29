package com.groceryrun.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Tests for loading the Grocery Run application context
 */
@SpringBootTest
@ActiveProfiles("test")
class AppApplicationTests {

    /**
     * Loads the application context
     */
    @Test
    void contextLoads() {
    }

}
