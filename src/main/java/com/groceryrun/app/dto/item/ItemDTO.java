package com.groceryrun.app.dto.item;

import java.util.List;

import com.groceryrun.app.entities.GroceryList;

public record ItemDTO(Integer id, String name, List<GroceryList> groceryLists) {
}
