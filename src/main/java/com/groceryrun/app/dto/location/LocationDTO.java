package com.groceryrun.app.dto.location;

import com.groceryrun.app.entities.Store;

public record LocationDTO(Integer id, String street, String city, String state, String zip, Store store) {
}
