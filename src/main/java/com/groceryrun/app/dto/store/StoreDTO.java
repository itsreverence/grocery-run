package com.groceryrun.app.dto.store;

import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.GroceryList;
import com.groceryrun.app.entities.Location;

import java.util.List;

public record StoreDTO(Integer id, String name, Location storeLocation, List<GroceryList> groceryLists, List<Aisle> aisles) {
}
