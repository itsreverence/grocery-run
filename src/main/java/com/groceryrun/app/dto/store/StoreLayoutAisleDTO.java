package com.groceryrun.app.dto.store;

import java.util.List;

/**
 * DTO for a store layout aisle
 * @param label label of the aisle
 * @param categories categories in the aisle
 */
public record StoreLayoutAisleDTO(String label, List<StoreLayoutCategoryDTO> categories) {
    
}
