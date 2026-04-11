package com.groceryrun.app.controller;

import com.groceryrun.app.dto.item.ItemDTO;
import com.groceryrun.app.dto.item.NewItemDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @PostMapping("admin/{categoryId}/items")
    public void addItem(Principal principal, @PathVariable Integer categoryId, @RequestBody NewItemDTO newItemDTO) {
        itemService.addItem(principal.getName(), categoryId, newItemDTO);
    }

    @DeleteMapping("admin/{categoryId}/items/{itemId}")
    public void deleteItem(Principal principal, @PathVariable Integer categoryId, @PathVariable Integer itemId) {
        itemService.deleteItem(principal.getName(), itemId, categoryId);
    }

    @PutMapping("admin/{id}/name")
    public void updateItemName(Principal principal, @PathVariable Integer id, @RequestBody NameChangeDTO nameChangeDTO) {
        itemService.updateItemName(principal.getName(), id, nameChangeDTO);
    }
}
