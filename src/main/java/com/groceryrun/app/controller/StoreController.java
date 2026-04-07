package com.groceryrun.app.controller;

import com.groceryrun.app.dto.store.NewStoreDTO;
import com.groceryrun.app.dto.store.StoreDTO;
import com.groceryrun.app.dto.store.StoreOwnersChangeDTO;
import com.groceryrun.app.dto.store.StoresAislesChangeDTO;
import com.groceryrun.app.dto.store.StoreLocationChangeDTO;
import com.groceryrun.app.dto.aisle.NewAisleDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.services.StoreService;
import com.groceryrun.app.services.UserService;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/stores")
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    public StoreController(StoreService storeService, UserService userService) {
        this.storeService = storeService;
        this.userService = userService;
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

    @PostMapping("admin/{id}/aisles")
    public void addAislesToStore(@PathVariable Integer id, @RequestBody NewAisleDTO newAisleDTO) {
        storeService.addAislesToStore(id, newAisleDTO);
    }

    @DeleteMapping("admin/{id}")
    public void deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id);
    }

    @DeleteMapping("admin/{id}/aisles/{aisleId}")
    public void deleteAisleFromStore(@PathVariable Integer id, @PathVariable Integer aisleId) {
        storeService.deleteAisleFromStore(id, aisleId);
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
