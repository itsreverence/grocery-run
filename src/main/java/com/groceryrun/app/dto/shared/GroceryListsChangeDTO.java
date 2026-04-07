package com.groceryrun.app.dto.shared;

import java.util.List;

import com.groceryrun.app.entities.GroceryList;

public record GroceryListsChangeDTO(List<GroceryList> newGroceryLists) {
    
}
