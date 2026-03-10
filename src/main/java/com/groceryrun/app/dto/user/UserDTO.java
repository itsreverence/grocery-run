package com.groceryrun.app.dto.user;

import com.groceryrun.app.entities.GroceryList;
import com.groceryrun.app.enums.Role;

import java.util.List;

public record UserDTO(Integer id, Role role, String username, List<GroceryList> groceryLists) {}
