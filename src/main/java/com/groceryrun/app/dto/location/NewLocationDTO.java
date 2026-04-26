package com.groceryrun.app.dto.location;

import com.groceryrun.app.entities.Store;

/**
 * DTO for a new location
 * @param street street of the new location
 * @param city city of the new location
 * @param state state of the new location
 * @param zip zip code of the new location
 * @param store store of the new location
 */
public record NewLocationDTO(String street, String city, String state, String zip, Store store) {
}
