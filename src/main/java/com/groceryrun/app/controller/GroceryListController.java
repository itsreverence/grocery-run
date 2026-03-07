package com.groceryrun.app.controller;

import com.groceryrun.app.dto.grocerylist.GroceryListDTO;
import com.groceryrun.app.dto.grocerylist.GroceryListNameChangeDTO;
import com.groceryrun.app.dto.grocerylist.NewGroceryListDTO;
import com.groceryrun.app.services.GroceryListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/grocery-lists")
public class GroceryListController {

    private final GroceryListService groceryListService;

    public GroceryListController(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    @GetMapping
    public List<GroceryListDTO> getAllGroceryLists() {
        return groceryListService.getAllGroceryLists();
    }

    @GetMapping("{id}")
    public GroceryListDTO getGroceryList(@PathVariable Integer id) {
        return groceryListService.getGroceryListById(id);
    }

    @PostMapping
    public void addGroceryList(@ModelAttribute NewGroceryListDTO newGroceryListDTO) {
        groceryListService.addGroceryList(newGroceryListDTO);
    }

    @DeleteMapping("{id}")
    public void deleteGroceryList(@PathVariable Integer id) {
        groceryListService.deleteGroceryList(id);
    }

    @PutMapping("{id}/name")
    public void updateGroceryListName(@PathVariable Integer id, @ModelAttribute GroceryListNameChangeDTO groceryListNameChangeDTO) {
        groceryListService.updateGroceryListName(id, groceryListNameChangeDTO);
    }
 }
