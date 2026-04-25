package com.groceryrun.app.repositories;

import com.groceryrun.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for user data
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Finds a user by their username
     * @param username username of the user
     * @return optional user
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user exists by their username
     * @param username username of the user
     * @return true if the user exists, false otherwise
     */
    boolean existsByUsername(String username);
}
