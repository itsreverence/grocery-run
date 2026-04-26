package com.groceryrun.app.dto.location;

import com.groceryrun.app.entities.Store;

/**
 * DTO for a location store change
 * @param newStore new store
 */
public record LocationStoreChangeDTO(Store newStore) {
    
}
