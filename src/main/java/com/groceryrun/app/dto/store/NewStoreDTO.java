package com.groceryrun.app.dto.store;

import com.groceryrun.app.dto.location.NewLocationDTO;

public record NewStoreDTO(String storeName, NewLocationDTO storeLocation) {
}
