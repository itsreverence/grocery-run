package com.groceryrun.app.controller;

import com.groceryrun.app.dto.store.NewStoreDTO;
import com.groceryrun.app.dto.store.StoreDTO;
import com.groceryrun.app.dto.store.StoreLayoutTransferDTO;
import com.groceryrun.app.dto.store.StoreOwnersChangeDTO;
import com.groceryrun.app.dto.store.StoresAislesChangeDTO;
import com.groceryrun.app.dto.store.StoreLocationChangeDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.services.StoreService;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller for stores
 */
@RestController
@RequestMapping("api/v1/stores")
public class StoreController {

    private final StoreService storeService; // Service for store data
    
    /**
     * Constructor for store controller
     * @param storeService service for store data
     */
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Retrieves all stores from the database
     * @return list of store DTOs
     */
    @GetMapping
    public List<StoreDTO> getAllStores() {
        return storeService.getAllStores();
    }

    /**
     * Retrieves a store by its id
     * @param principal principal of the user
     * @param id id of the store
     * @return store DTO
     */
    @GetMapping("admin/{id}")
    public StoreDTO getStore(Principal principal, @PathVariable Integer id) {
        return storeService.getStoreById(principal.getName(), id);
    }

    /**
     * Retrieves all stores owned by the user
     * @param principal principal of the user
     * @return list of store DTOs
     */
    @GetMapping("admin/owned")
    public List<StoreDTO> getStoresByOwner(Principal principal) {
        return storeService.getStoresByOwner(principal.getName());
    }

    /**
     * Exports a store
     * @param principal principal of the user
     * @param id id of the store
     * @return store layout transfer DTO
     */
    @GetMapping("admin/{id}/export")
    public StoreLayoutTransferDTO exportStore(Principal principal, @PathVariable Integer id) {
        return storeService.exportStore(principal.getName(), id);
    }

    /**
     * Adds a new store
     * @param newStoreDTO new store data
     * @param principal principal of the user
     */
    @PostMapping("admin")
    public void addNewStore(@RequestBody NewStoreDTO newStoreDTO, Principal principal) {
        storeService.addStore(principal.getName(), newStoreDTO);
    }

    /**
     * Imports a store
     * @param principal principal of the user
     * @param id id of the store
     * @param storeLayoutTransferDTO store layout transfer data
     */
    @PostMapping("admin/{id}/import")
    public void importStore(Principal principal, @PathVariable Integer id, @RequestBody StoreLayoutTransferDTO storeLayoutTransferDTO) {
        storeService.importStore(principal.getName(), id, storeLayoutTransferDTO);
    }

    /**
     * Deletes a store
     * @param principal principal of the user
     * @param id id of the store
     */
    @DeleteMapping("admin/{id}")
    public void deleteStore(Principal principal, @PathVariable Integer id) {
        storeService.deleteStore(principal.getName(), id);
    }

    /**
     * Updates the name of a store
     * @param principal principal of the user
     * @param id id of the store
     * @param nameChangeDTO new name
     */
    @PutMapping("admin/{id}/name")
    public void updateStoreName(Principal principal, @PathVariable Integer id, @RequestBody NameChangeDTO nameChangeDTO) {
        storeService.updateStoreName(principal.getName(), id, nameChangeDTO);
    }

    /**
     * Updates the location of a store
     * @param principal principal of the user
     * @param id id of the store
     * @param storeLocationChangeDTO new location
     */
    @PutMapping("admin/{id}/location")
    public void updateStoreLocation(Principal principal, @PathVariable Integer id, @RequestBody StoreLocationChangeDTO storeLocationChangeDTO) {
        storeService.updateStoreLocation(principal.getName(), id, storeLocationChangeDTO);
    }

    /**
     * Updates the aisles of a store
     * @param principal principal of the user
     * @param id id of the store
     * @param storesAislesChangeDTO new aisles
     */
    @PutMapping("admin/{id}/aisles")
    public void updateStoreAisles(Principal principal, @PathVariable Integer id, @ModelAttribute StoresAislesChangeDTO storesAislesChangeDTO) {
        storeService.updateStoreAisles(principal.getName(), id, storesAislesChangeDTO);
    }

    /**
     * Updates the owners of a store
     * @param principal principal of the user
     * @param id id of the store
     * @param storeOwnersChangeDTO new owners
     */
    @PutMapping("admin/{id}/owners")
    public void updateStoreOwners(Principal principal, @PathVariable Integer id, @RequestBody StoreOwnersChangeDTO storeOwnersChangeDTO) {
        storeService.updateStoreOwners(principal.getName(), id, storeOwnersChangeDTO);
    }
}
