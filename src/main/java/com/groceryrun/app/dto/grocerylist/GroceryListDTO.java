package com.groceryrun.app.dto.grocerylist;

import java.util.List;

import com.groceryrun.app.entities.Item;
import com.groceryrun.app.entities.User;

public record GroceryListDTO(Integer id, String name, User groceryListOwner, List<Item> groceryListItems) {
}
