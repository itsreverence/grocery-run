package com.groceryrun.app.dto;

import com.groceryrun.app.enums.Role;

public record UserDTO(Integer id, Role role, String username) {}
