package com.groceryrun.app.dto.store;

import java.util.List;

/**
 * DTO for a store layout category
 * @param label label of the category
 * @param items items in the category
 */
public record StoreLayoutCategoryDTO(String label, List<StoreLayoutItemDTO> items) {
    
}
