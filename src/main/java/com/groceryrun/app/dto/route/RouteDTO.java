package com.groceryrun.app.dto.route;

import java.util.List;

import com.groceryrun.app.dto.item.ItemDTO;

/**
 * DTO for a route
 * @param storeId id of the store
 * @param storeName name of the store
 * @param groceryListId id of the grocery list
 * @param groceryListName name of the grocery list
 * @param stops stops on the route
 * @param unmatchedItems unmatched items on the route
 */
public record RouteDTO(Integer storeId, String storeName, Integer groceryListId, String groceryListName, List<RouteStopDTO> stops, List<ItemDTO> unmatchedItems)  {
    
}
