package com.groceryrun.app.dto.grocerylist;

import com.groceryrun.app.entities.User;

public record NewGroceryListDTO(String groceryListName, User groceryListOwner) {
}
