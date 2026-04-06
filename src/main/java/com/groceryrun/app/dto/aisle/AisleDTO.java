package com.groceryrun.app.dto.aisle;

import java.util.List;

import com.groceryrun.app.entities.Category;
import com.groceryrun.app.entities.Store;

public record AisleDTO(Integer id, String label, Store store, List<Category> aisleCategories) {
}
