package com.groceryrun.app.dto.item;

import com.groceryrun.app.entities.GroceryList;

import java.util.List;

public record ItemGroceryListsChangeDTO(List<GroceryList> newGroceryLists) {
}
