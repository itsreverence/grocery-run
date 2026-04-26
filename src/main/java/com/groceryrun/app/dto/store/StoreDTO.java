package com.groceryrun.app.dto.store;

import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.location.LocationDTO;
import com.groceryrun.app.dto.user.UserDTO;

import java.util.List;

/**
 * DTO for a store
 * @param id id of the store
 * @param name name of the store
 * @param storeLocation location of the store
 * @param owners owners of the store
 * @param aisles aisles of the store
 */
public record StoreDTO(Integer id, String name, LocationDTO storeLocation, List<UserDTO> owners, List<AisleDTO> aisles) {
}
