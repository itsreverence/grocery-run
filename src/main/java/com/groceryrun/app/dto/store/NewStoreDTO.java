package com.groceryrun.app.dto.store;

import java.util.List;

import com.groceryrun.app.entities.Location;
import com.groceryrun.app.entities.User;

public record NewStoreDTO(String storeName, Location storeLocation, List<User> owners) {
}
