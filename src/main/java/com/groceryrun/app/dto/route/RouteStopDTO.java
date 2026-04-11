package com.groceryrun.app.dto.route;

import java.util.List;

import com.groceryrun.app.dto.item.ItemDTO;

public record RouteStopDTO(Integer aisleId, String aisleLabel, List<ItemDTO> items) {
    
}
