package com.groceryrun.app.dto.store;

import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.location.LocationDTO;
import com.groceryrun.app.dto.user.UserDTO;

import java.util.List;

public record StoreDTO(Integer id, String name, LocationDTO storeLocation, List<UserDTO> owners, List<AisleDTO> aisles) {
}
