package com.groceryrun.app.dto.store;

import java.util.List;

public record StoreLayoutCategoryDTO(String label, List<StoreLayoutItemDTO> items) {
    
}
