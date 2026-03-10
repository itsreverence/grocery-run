package com.groceryrun.app.dto.store;

import com.groceryrun.app.entities.GroceryList;

import java.util.List;

public record StoreGroceryListsChangeDTO(List<GroceryList> newGroceryLists) {
}
