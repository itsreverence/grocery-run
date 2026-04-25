package com.groceryrun.app.dto.user;

import com.groceryrun.app.enums.Role;

/**
 * DTO for a user
 * @param id id of the user
 * @param role role of the user
 * @param username username of the user
 */
public record UserDTO(Integer id, Role role, String username) {
    
}
