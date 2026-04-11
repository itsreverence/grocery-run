package com.groceryrun.app.dto.route;

import java.util.List;

import com.groceryrun.app.dto.item.ItemDTO;

public record RouteDTO(Integer storeId, String storeName, Integer groceryListId, String groceryListName, List<RouteStopDTO> stops, List<ItemDTO> unmatchedItems)  {
    
}
