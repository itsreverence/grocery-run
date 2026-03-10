package com.groceryrun.app.controller;

import com.groceryrun.app.dto.store.NewStoreDTO;
import com.groceryrun.app.dto.store.StoreDTO;
import com.groceryrun.app.dto.store.StoreGroceryListsChangeDTO;
import com.groceryrun.app.dto.store.StoreNameChangeDTO;
import com.groceryrun.app.services.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<StoreDTO> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("{id}")
    public StoreDTO getStore(@PathVariable Integer id) {
        return storeService.getStoreById(id);
    }

    @PostMapping
    public void addNewStore(@ModelAttribute NewStoreDTO newStoreDTO) {
        storeService.addStore(newStoreDTO);
    }

    @DeleteMapping("{id}")
    public void deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id);
    }

    @PutMapping("{id}/name")
    public void updateStoreName(@PathVariable Integer id, @ModelAttribute StoreNameChangeDTO storeNameChangeDTO) {
        storeService.updateStoreName(id, storeNameChangeDTO);
    }

    @PutMapping("{id}/grocery-lists")
    public void updateStoreGroceryLists(@PathVariable Integer id, @ModelAttribute StoreGroceryListsChangeDTO storeGroceryListsChangeDTO) {
        storeService.updateStoreGroceryLists(id, storeGroceryListsChangeDTO);
    }
}
