package com.groceryrun.app.dto.grocerylist;

import java.util.List;

/**
 * DTO for a grocery list transfer
 * @param groceryListName name of the grocery list
 * @param items items in the grocery list
 */
public record GroceryListTransferDTO(String groceryListName, List<GroceryListTransferItemDTO> items) {
    
}
