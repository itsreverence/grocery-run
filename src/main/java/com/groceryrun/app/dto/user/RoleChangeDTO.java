package com.groceryrun.app.dto.user;

import com.groceryrun.app.enums.Role;

/**
 * DTO for a role change
 * @param newRole new role
 */
public record RoleChangeDTO(Role newRole) {
    
}
