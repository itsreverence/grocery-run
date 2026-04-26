package com.groceryrun.app.dto.user;

/**
 * DTO for a password change
 * @param currentPassword current password
 * @param newPassword new password
 */
public record PasswordChangeDTO(String currentPassword, String newPassword) {
    
}
