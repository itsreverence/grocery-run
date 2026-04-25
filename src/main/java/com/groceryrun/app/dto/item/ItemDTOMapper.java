package com.groceryrun.app.dto.item;

import com.groceryrun.app.entities.Item;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper for item data
 */
@Service
public class ItemDTOMapper implements Function<Item, ItemDTO> {    
    /**
     * Maps an item entity to an item DTO
     * @param item item entity
     * @return item DTO
     */
    @Override
    public ItemDTO apply(Item item) {
        return new ItemDTO(item.getId(), item.getName());
    }
}
