package com.groceryrun.app.controller;

import com.groceryrun.app.dto.item.ItemDTO;
import com.groceryrun.app.dto.item.NewItemDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller for items
 */
@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    private final ItemService itemService; // Service for item data
    
    /**
     * Constructor for item controller
     * @param itemService service for item data
     */
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Retrieves all items from the database
     * @return list of item DTOs
     */
    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    /**
     * Retrieves an item by its id
     * @param id id of the item
     * @return item DTO
     */
    @GetMapping("{id}")
    public ItemDTO getItem(@PathVariable Integer id) {
        return itemService.getItemById(id);
    }

    /**
     * Adds an item to a category
     * @param principal principal of the user
     * @param categoryId id of the category
     * @param newItemDTO new item data
     */
    @PostMapping("admin/{categoryId}/items")
    public void addItem(Principal principal, @PathVariable Integer categoryId, @RequestBody NewItemDTO newItemDTO) {
        itemService.addItem(principal.getName(), categoryId, newItemDTO);
    }

    /**
     * Deletes an item from a category
     * @param principal principal of the user
     * @param categoryId id of the category
     * @param itemId id of the item
     */
    @DeleteMapping("admin/{categoryId}/items/{itemId}")
    public void deleteItem(Principal principal, @PathVariable Integer categoryId, @PathVariable Integer itemId) {
        itemService.deleteItem(principal.getName(), itemId, categoryId);
    }

    /**
     * Updates the name of an item
     * @param principal principal of the user
     * @param id id of the item
     * @param nameChangeDTO new name
     */
    @PutMapping("admin/{id}/name")
    public void updateItemName(Principal principal, @PathVariable Integer id, @RequestBody NameChangeDTO nameChangeDTO) {
        itemService.updateItemName(principal.getName(), id, nameChangeDTO);
    }
}
