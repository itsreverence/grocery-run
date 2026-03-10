package com.groceryrun.app.dto.user;

import com.groceryrun.app.entities.GroceryList;
import com.groceryrun.app.enums.AccountRole;

import java.util.List;

public record UserDTO(Integer id, AccountRole accountRole, String username, List<GroceryList> groceryLists) {}
