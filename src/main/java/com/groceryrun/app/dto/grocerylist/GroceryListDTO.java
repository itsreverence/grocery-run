package com.groceryrun.app.dto.grocerylist;

import java.util.List;

import com.groceryrun.app.dto.item.ItemDTO;
import com.groceryrun.app.dto.user.UserDTO;

/**
 * DTO for a grocery list
 * @param id id of the grocery list
 * @param name name of the grocery list
 * @param groceryListOwner owner of the grocery list
 * @param groceryListItems items in the grocery list
 */
public record GroceryListDTO(Integer id, String name, UserDTO groceryListOwner, List<ItemDTO> groceryListItems) {
}
