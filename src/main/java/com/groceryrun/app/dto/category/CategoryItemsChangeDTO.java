package com.groceryrun.app.dto.category;

import java.util.List;

import com.groceryrun.app.entities.Item;

public record CategoryItemsChangeDTO(List<Item> newCategoryItems) {
    
}
