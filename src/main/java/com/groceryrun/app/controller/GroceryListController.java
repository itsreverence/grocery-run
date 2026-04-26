package com.groceryrun.app.controller;


import com.groceryrun.app.dto.grocerylist.GroceryListDTO;
import com.groceryrun.app.dto.grocerylist.GroceryListTransferDTO;
import com.groceryrun.app.dto.grocerylist.NewGroceryListDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.services.GroceryListService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller for grocery lists
 */
@RestController
@RequestMapping("api/v1/grocery-lists")
public class GroceryListController {

    private final GroceryListService groceryListService; // Service for grocery list data
    
    /**
     * Constructor for grocery list controller
     * @param groceryListService service for grocery list data
     */

    public GroceryListController(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    /**
     * Retrieves all grocery lists from the database
     * @param principal principal of the user
     * @return list of grocery list DTOs
     */
    @GetMapping
    public List<GroceryListDTO> getAllGroceryLists(Principal principal) {
        return groceryListService.getAllGroceryLists(principal.getName());
    }

    /**
     * Retrieves a grocery list by its id
     * @param principal principal of the user
     * @param id id of the grocery list
     * @return grocery list DTO
     */
    @GetMapping("{id}")
    public GroceryListDTO getGroceryList(Principal principal, @PathVariable Integer id) {
        return groceryListService.getGroceryListById(principal.getName(), id);
    }

    /**
     * Exports a grocery list
     * @param principal principal of the user
     * @param id id of the grocery list
     * @return grocery list transfer DTO
     */
    @GetMapping("{id}/export")
    public GroceryListTransferDTO exportGroceryList(Principal principal, @PathVariable Integer id) {
        return groceryListService.exportGroceryList(principal.getName(), id);
    }

    /**
     * Adds a grocery list
     * @param principal principal of the user
     * @param newGroceryListDTO new grocery list data
     */
    @PostMapping
    public void addGroceryList(Principal principal, @RequestBody NewGroceryListDTO newGroceryListDTO) {
        groceryListService.addGroceryList(principal.getName(), newGroceryListDTO);
    }

    /**
     * Adds an item to a grocery list
     * @param principal principal of the user
     * @param id id of the grocery list
     * @param itemId id of the item
     */
    @PostMapping("{id}/items/{itemId}")
    public void addItemToGroceryList(Principal principal, @PathVariable Integer id, @PathVariable Integer itemId) {
        groceryListService.addItemToGroceryList(principal.getName(), id, itemId);
    }

    /**
     * Imports a grocery list
     * @param principal principal of the user
     * @param groceryListTransferDTO grocery list transfer data
     */
    @PostMapping("import")
    public void importGroceryList(Principal principal, @RequestBody GroceryListTransferDTO groceryListTransferDTO) {
        groceryListService.importGroceryList(principal.getName(), groceryListTransferDTO);
    }

    /**
     * Deletes a grocery list
     * @param principal principal of the user
     * @param id id of the grocery list
     */
    @DeleteMapping("{id}")
    public void deleteGroceryList(Principal principal, @PathVariable Integer id) {
        groceryListService.deleteGroceryList(principal.getName(), id);
    }

    /**
     * Removes an item from a grocery list
     * @param principal principal of the user
     * @param id id of the grocery list
     * @param itemId id of the item
     */
    @DeleteMapping("{id}/items/{itemId}")
    public void removeItemFromGroceryList(Principal principal, @PathVariable Integer id, @PathVariable Integer itemId) {
        groceryListService.removeItemFromGroceryList(principal.getName(), id, itemId);
    }

    /**
     * Updates the name of a grocery list
     * @param principal principal of the user
     * @param id id of the grocery list
     * @param nameChangeDTO new name
     */
    @PutMapping("{id}/name")
    public void updateGroceryListName(Principal principal, @PathVariable Integer id, @RequestBody NameChangeDTO nameChangeDTO) {
        groceryListService.updateGroceryListName(principal.getName(), id, nameChangeDTO);
    }
 }
