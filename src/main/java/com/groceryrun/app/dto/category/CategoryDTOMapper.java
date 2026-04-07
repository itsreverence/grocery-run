package com.groceryrun.app.dto.category;

import com.groceryrun.app.dto.item.ItemDTOMapper;
import com.groceryrun.app.entities.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {
    private final ItemDTOMapper itemDTOMapper;

    public CategoryDTOMapper(ItemDTOMapper itemDTOMapper) {
        this.itemDTOMapper = itemDTOMapper;
    }
    
    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(category.getId(), category.getLabel(), category.getItems().stream().map(itemDTOMapper).collect(Collectors.toList()));
    }
}
