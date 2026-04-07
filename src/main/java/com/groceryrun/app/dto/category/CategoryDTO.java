package com.groceryrun.app.dto.category;

import java.util.List;

import com.groceryrun.app.dto.item.ItemDTO;

public record CategoryDTO(Integer id, String label, List<ItemDTO> categoryItems) {
}
