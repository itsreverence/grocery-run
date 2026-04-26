package com.groceryrun.app.dto.category;

import java.util.List;

import com.groceryrun.app.dto.item.ItemDTO;

/**
 * DTO for a category in the grocery store
 * @param id id of the category
 * @param label label of the category
 * @param categoryItems items in the category
 */
public record CategoryDTO(Integer id, String label, List<ItemDTO> categoryItems) {
}
