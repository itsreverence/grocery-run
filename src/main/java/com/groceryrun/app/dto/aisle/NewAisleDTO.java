package com.groceryrun.app.dto.aisle;

import com.groceryrun.app.entities.Store;

public record NewAisleDTO(String label, Store aisleStore) {
}
