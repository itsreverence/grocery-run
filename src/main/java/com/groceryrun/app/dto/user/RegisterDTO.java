package com.groceryrun.app.dto.user;

/**
 * DTO for a user registration
 * @param username username of the user
 * @param password password of the user
 */
public record RegisterDTO(String username, String password) {
    
}
