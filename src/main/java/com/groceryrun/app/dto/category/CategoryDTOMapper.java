package com.groceryrun.app.dto.category;

import com.groceryrun.app.dto.item.ItemDTOMapper;
import com.groceryrun.app.entities.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mapper for category data
 */
@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {
    private final ItemDTOMapper itemDTOMapper; // Mapper for item data

    /**
     * Constructor for category DTO mapper
     * @param itemDTOMapper mapper for item data
     */
    public CategoryDTOMapper(ItemDTOMapper itemDTOMapper) {
        this.itemDTOMapper = itemDTOMapper;
    }
    
    /**
     * Maps a category entity to a category DTO
     * @param category category entity
     * @return category DTO
     */
    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(category.getId(), category.getLabel(), category.getItems().stream().map(itemDTOMapper).collect(Collectors.toList()));
    }
}
