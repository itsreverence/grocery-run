package com.groceryrun.app.dto.item;

import com.groceryrun.app.entities.Category;

public record NewItemDTO(String name, Category itemCategory) {
}
