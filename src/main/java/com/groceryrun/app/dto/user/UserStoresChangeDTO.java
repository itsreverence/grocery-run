package com.groceryrun.app.dto.user;

import java.util.List;

import com.groceryrun.app.entities.Store;

public record UserStoresChangeDTO(List<Store> newStores) {
    
}
