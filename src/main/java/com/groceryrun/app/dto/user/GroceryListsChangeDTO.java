package com.groceryrun.app.dto.user;

import com.groceryrun.app.entities.GroceryList;

import java.util.List;

public record GroceryListsChangeDTO(List<GroceryList> newGroceryLists) {
}
