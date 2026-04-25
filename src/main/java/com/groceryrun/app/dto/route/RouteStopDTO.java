package com.groceryrun.app.dto.route;

import java.util.List;

import com.groceryrun.app.dto.item.ItemDTO;

/**
 * DTO for a route stop
 * @param aisleId id of the aisle
 * @param aisleLabel label of the aisle
 * @param items items in the route stop
 */
public record RouteStopDTO(Integer aisleId, String aisleLabel, List<ItemDTO> items) {
    
}
