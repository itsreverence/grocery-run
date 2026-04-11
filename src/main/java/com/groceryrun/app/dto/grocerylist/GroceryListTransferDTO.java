package com.groceryrun.app.dto.grocerylist;

import java.util.List;

public record GroceryListTransferDTO(String groceryListName, List<GroceryListTransferItemDTO> items) {
    
}
