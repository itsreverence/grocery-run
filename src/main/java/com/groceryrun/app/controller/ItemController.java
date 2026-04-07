package com.groceryrun.app.controller;

import com.groceryrun.app.dto.item.ItemCategoryChangeDTO;
import com.groceryrun.app.dto.item.ItemDTO;
import com.groceryrun.app.dto.item.NewItemDTO;
import com.groceryrun.app.dto.shared.GroceryListsChangeDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("{id}")
    public ItemDTO getItem(@PathVariable Integer id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    public void addItem(@ModelAttribute NewItemDTO newItemDTO) {
        itemService.addItem(newItemDTO);
    }

    @DeleteMapping("{id}")
    public void deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
    }

    @PutMapping("{id}/name")
    public void updateItemName(@PathVariable Integer id, @ModelAttribute NameChangeDTO nameChangeDTO) {
        itemService.updateItemName(id, nameChangeDTO);
    }

    @PutMapping("{id}/grocery-lists")
    public void updateItemGroceryLists(@PathVariable Integer id, @ModelAttribute GroceryListsChangeDTO groceryListsChangeDTO) {
        itemService.updateItemGroceryLists(id, groceryListsChangeDTO);
    }

    @PutMapping("{id}/category")
    public void updateItemCategory(@PathVariable Integer id, @ModelAttribute ItemCategoryChangeDTO itemCategoryChangeDTO) {
        itemService.updateItemCategory(id, itemCategoryChangeDTO);
    }
}
