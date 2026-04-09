package com.groceryrun.app.controller;

import com.groceryrun.app.dto.store.NewStoreDTO;
import com.groceryrun.app.dto.store.StoreDTO;
import com.groceryrun.app.dto.store.StoreOwnersChangeDTO;
import com.groceryrun.app.dto.store.StoresAislesChangeDTO;
import com.groceryrun.app.dto.store.StoreLocationChangeDTO;
import com.groceryrun.app.dto.aisle.NewAisleDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.services.StoreService;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("admin/{id}")
    public StoreDTO getStore(@PathVariable Integer id) {
        return storeService.getStoreById(id);
    }

    @GetMapping("admin/owned")
    public List<StoreDTO> getStoresByOwner(Principal principal) {
        return storeService.getStoresByOwner(principal.getName());
    }

    @PostMapping("admin")
    public void addNewStore(@RequestBody NewStoreDTO newStoreDTO, Principal principal) {
        storeService.addStore(principal.getName(), newStoreDTO);
    }

    @DeleteMapping("admin/{id}")
    public void deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id);
    }

    @PutMapping("admin/{id}/name")
    public void updateStoreName(@PathVariable Integer id, @RequestBody NameChangeDTO nameChangeDTO) {
        storeService.updateStoreName(id, nameChangeDTO);
    }

    @PutMapping("admin/{id}/location")
    public void updateStoreLocation(@PathVariable Integer id, @RequestBody StoreLocationChangeDTO storeLocationChangeDTO) {
        storeService.updateStoreLocation(id, storeLocationChangeDTO);
    }

    @PutMapping("admin/{id}/aisles")
    public void updateStoreAisles(@PathVariable Integer id, @ModelAttribute StoresAislesChangeDTO storesAislesChangeDTO) {
        storeService.updateStoreAisles(id, storesAislesChangeDTO);
    }

    @PutMapping("admin/{id}/owners")
    public void updateStoreOwners(@PathVariable Integer id, @RequestBody StoreOwnersChangeDTO storeOwnersChangeDTO) {
        storeService.updateStoreOwners(id, storeOwnersChangeDTO);
    }
}
