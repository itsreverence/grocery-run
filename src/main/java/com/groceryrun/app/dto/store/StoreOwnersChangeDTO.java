package com.groceryrun.app.dto.store;

import java.util.List;

import com.groceryrun.app.entities.User;

public record StoreOwnersChangeDTO(List<User> newOwners) {
    
}
