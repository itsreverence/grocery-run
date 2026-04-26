package com.groceryrun.app.dto.store;

import com.groceryrun.app.dto.location.NewLocationDTO;

/**
 * DTO for a new store
 * @param storeName name of the new store
 * @param storeLocation location of the new store
 */
public record NewStoreDTO(String storeName, NewLocationDTO storeLocation) {
}
