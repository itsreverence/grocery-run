package com.groceryrun.app.dto.store;

import java.util.List;

/**
 * DTO for a store layout transfer
 * @param storeName name of the store
 * @param aisles aisles in the store
 */
public record StoreLayoutTransferDTO(String storeName, List<StoreLayoutAisleDTO> aisles) {
    
}
