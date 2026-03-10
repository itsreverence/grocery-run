package com.groceryrun.app.dto.grocerylist;

import com.groceryrun.app.entities.GroceryList;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GroceryListDTOMapper implements Function<GroceryList, GroceryListDTO> {
    @Override
    public GroceryListDTO apply(GroceryList groceryList) {
        return new GroceryListDTO(groceryList.getId(), groceryList.getName(), groceryList.getGroceryListOwner(), groceryList.getGroceryListStore(), groceryList.getListRoute(), groceryList.getGroceryListItems(), groceryList.getGroceryListItemsQuantities(), groceryList.getGroceryListItemsFoundStatus());
    }
}
