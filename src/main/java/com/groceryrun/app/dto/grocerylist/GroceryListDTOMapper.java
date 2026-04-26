package com.groceryrun.app.dto.grocerylist;

import com.groceryrun.app.dto.item.ItemDTOMapper;
import com.groceryrun.app.dto.user.UserDTOMapper;
import com.groceryrun.app.entities.GroceryList;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mapper for grocery list data
 */
@Service
public class GroceryListDTOMapper implements Function<GroceryList, GroceryListDTO> {
    private final UserDTOMapper userDTOMapper; // Mapper for user data
    private final ItemDTOMapper itemDTOMapper; // Mapper for item data

    /**
     * Constructor for grocery list DTO mapper
     * @param userDTOMapper mapper for user data
     * @param itemDTOMapper mapper for item data
     */

    public GroceryListDTOMapper(UserDTOMapper userDTOMapper, ItemDTOMapper itemDTOMapper) {
        this.userDTOMapper = userDTOMapper;
        this.itemDTOMapper = itemDTOMapper;
    }
    
    /**
     * Maps a grocery list entity to a grocery list DTO
     * @param groceryList grocery list entity
     * @return grocery list DTO
     */
    @Override
    public GroceryListDTO apply(GroceryList groceryList) {
        return new GroceryListDTO(groceryList.getId(), groceryList.getName(), userDTOMapper.apply(groceryList.getOwner()), groceryList.getItems().stream().map(itemDTOMapper).collect(Collectors.toList()));
    }
}
