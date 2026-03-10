package com.groceryrun.app.dto.aisle;

import java.util.List;

import com.groceryrun.app.entities.Category;

public record AisleCategoriesChangeDTO(List<Category> newAisleCategories) {
    
}
