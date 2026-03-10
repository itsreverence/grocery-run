package com.groceryrun.app.dto.grocerylist;

import com.groceryrun.app.entities.Route;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.entities.User;

public record GroceryListDTO(Integer id, String name, User groceryListOwner, Store groceryListStore, Route listRoute) {
}
