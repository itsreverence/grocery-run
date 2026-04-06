package com.groceryrun.app.dto.store;

import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.Location;
import com.groceryrun.app.entities.User;

import java.util.List;

public record StoreDTO(Integer id, String name, Location storeLocation, List<User> owners, List<Aisle> aisles) {
}
