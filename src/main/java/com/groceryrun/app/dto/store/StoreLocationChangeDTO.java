package com.groceryrun.app.dto.store;

import com.groceryrun.app.dto.location.NewLocationDTO;

/**
 * DTO for a store location change
 * @param newLocationDTO new location
 */
public record StoreLocationChangeDTO(NewLocationDTO newLocationDTO) {
    
}
