package com.groceryrun.app.dto.category;

import java.util.List;

import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.Item;

public record CategoryDTO(Integer id, String label, Aisle categoryAisle, List<Item> categoryItems) {
}
