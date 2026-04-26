package com.groceryrun.app.dto.aisle;

import java.util.List;

import com.groceryrun.app.dto.category.CategoryDTO;

/**
 * DTO for an aisle in the grocery store
 * @param id id of the aisle
 * @param label label of the aisle
 * @param aisleCategories categories in the aisle
 */
public record AisleDTO(Integer id, String label, List<CategoryDTO> aisleCategories) {
}
