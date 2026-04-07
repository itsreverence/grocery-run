package com.groceryrun.app.dto.grocerylist;

import com.groceryrun.app.dto.item.ItemDTOMapper;
import com.groceryrun.app.dto.user.UserDTOMapper;
import com.groceryrun.app.entities.GroceryList;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GroceryListDTOMapper implements Function<GroceryList, GroceryListDTO> {
    private final UserDTOMapper userDTOMapper;
    private final ItemDTOMapper itemDTOMapper;

    public GroceryListDTOMapper(UserDTOMapper userDTOMapper, ItemDTOMapper itemDTOMapper) {
        this.userDTOMapper = userDTOMapper;
        this.itemDTOMapper = itemDTOMapper;
    }
    
    @Override
    public GroceryListDTO apply(GroceryList groceryList) {
        return new GroceryListDTO(groceryList.getId(), groceryList.getName(), userDTOMapper.apply(groceryList.getOwner()), groceryList.getItems().stream().map(itemDTOMapper).collect(Collectors.toList()));
    }
}
