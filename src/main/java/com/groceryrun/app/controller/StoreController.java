package com.groceryrun.app.controller;

import com.groceryrun.app.entities.Store;
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
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("{id}")
    public Store getStore(@PathVariable Integer id) {
        return storeService.getStoreById(id);
    }

    @PostMapping
    public void addNewStore(@RequestBody Store store) {
        storeService.addStore(store);
    }

    @DeleteMapping("{id}")
    public void deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id);
    }

    @PutMapping("{id}/name")
    public void updateStoreName(@PathVariable Integer id, @RequestBody Store store) {
        storeService.updateStoreName(id, store.getName());
    }

}
