package com.groceryrun.app.dto.grocerylist;

import java.util.List;

public record GroceryListItemsFoundStatusChangeDTO(List<Boolean> newGroceryListItemsFoundStatus) {
}
