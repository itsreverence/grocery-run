package com.groceryrun.app.dto.store;

import java.util.List;

public record StoreLayoutTransferDTO(String storeName, List<StoreLayoutAisleDTO> aisles) {
    
}
