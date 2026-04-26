package com.groceryrun.app.dto.store;

import java.util.List;

/**
 * DTO for a store owners change
 * @param newOwnerIds new owner ids
 */
public record StoreOwnersChangeDTO(List<Integer> newOwnerIds) {
    
}
