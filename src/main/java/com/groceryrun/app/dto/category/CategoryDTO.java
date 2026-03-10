package com.groceryrun.app.dto.category;

import com.groceryrun.app.entities.Aisle;

public record CategoryDTO(Integer id, String label, Aisle categoryAisle) {
}
