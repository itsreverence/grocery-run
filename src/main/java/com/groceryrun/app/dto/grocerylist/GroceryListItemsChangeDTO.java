package com.groceryrun.app.dto.grocerylist;

import java.util.List;

import com.groceryrun.app.entities.Item;

public record GroceryListItemsChangeDTO(List<Item> newGroceryListItems) { 
}
