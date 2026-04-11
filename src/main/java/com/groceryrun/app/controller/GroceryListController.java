package com.groceryrun.app.controller;


import com.groceryrun.app.dto.grocerylist.GroceryListDTO;
import com.groceryrun.app.dto.grocerylist.GroceryListTransferDTO;
import com.groceryrun.app.dto.grocerylist.NewGroceryListDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.services.GroceryListService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/grocery-lists")
public class GroceryListController {

    private final GroceryListService groceryListService;

    public GroceryListController(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    @GetMapping
    public List<GroceryListDTO> getAllGroceryLists(Principal principal) {
        return groceryListService.getAllGroceryLists(principal.getName());
    }

    @GetMapping("{id}")
    public GroceryListDTO getGroceryList(Principal principal, @PathVariable Integer id) {
        return groceryListService.getGroceryListById(principal.getName(), id);
    }

    @GetMapping("{id}/export")
    public GroceryListTransferDTO exportGroceryList(Principal principal, @PathVariable Integer id) {
        return groceryListService.exportGroceryList(principal.getName(), id);
    }

    @PostMapping
    public void addGroceryList(Principal principal, @RequestBody NewGroceryListDTO newGroceryListDTO) {
        groceryListService.addGroceryList(principal.getName(), newGroceryListDTO);
    }

    @PostMapping("{id}/items/{itemId}")
    public void addItemToGroceryList(Principal principal, @PathVariable Integer id, @PathVariable Integer itemId) {
        groceryListService.addItemToGroceryList(principal.getName(), id, itemId);
    }

    @PostMapping("import")
    public void importGroceryList(Principal principal, @RequestBody GroceryListTransferDTO groceryListTransferDTO) {
        groceryListService.importGroceryList(principal.getName(), groceryListTransferDTO);
    }

    @DeleteMapping("{id}")
    public void deleteGroceryList(Principal principal, @PathVariable Integer id) {
        groceryListService.deleteGroceryList(principal.getName(), id);
    }

    @DeleteMapping("{id}/items/{itemId}")
    public void removeItemFromGroceryList(Principal principal, @PathVariable Integer id, @PathVariable Integer itemId) {
        groceryListService.removeItemFromGroceryList(principal.getName(), id, itemId);
    }

    @PutMapping("{id}/name")
    public void updateGroceryListName(Principal principal, @PathVariable Integer id, @RequestBody NameChangeDTO nameChangeDTO) {
        groceryListService.updateGroceryListName(principal.getName(), id, nameChangeDTO);
    }
 }
