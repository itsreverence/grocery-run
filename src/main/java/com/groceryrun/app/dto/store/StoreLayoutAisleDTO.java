package com.groceryrun.app.dto.store;

import java.util.List;

public record StoreLayoutAisleDTO(String label, List<StoreLayoutCategoryDTO> categories) {
    
}
