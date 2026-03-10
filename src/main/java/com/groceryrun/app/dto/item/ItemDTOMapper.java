package com.groceryrun.app.dto.item;

import com.groceryrun.app.entities.Item;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ItemDTOMapper implements Function<Item, ItemDTO> {
    @Override
    public ItemDTO apply(Item item) {
        return new ItemDTO(item.getId(), item.getName(), item.getGroceryLists());
    }
}
