package com.groceryrun.app.dto.grocerylist;

import java.util.List;

import com.groceryrun.app.dto.item.ItemDTO;
import com.groceryrun.app.dto.user.UserDTO;

public record GroceryListDTO(Integer id, String name, UserDTO groceryListOwner, List<ItemDTO> groceryListItems) {
}
