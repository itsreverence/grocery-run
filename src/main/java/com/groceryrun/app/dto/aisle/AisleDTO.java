package com.groceryrun.app.dto.aisle;

import java.util.List;

import com.groceryrun.app.dto.category.CategoryDTO;

public record AisleDTO(Integer id, String label, List<CategoryDTO> aisleCategories) {
}
