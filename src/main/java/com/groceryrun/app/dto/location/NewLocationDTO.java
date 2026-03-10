package com.groceryrun.app.dto.location;

import com.groceryrun.app.entities.Store;

public record NewLocationDTO(String street, String city, String state, String zip, Store store) {
}
