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
    public StoreDTO getStore(Principal principal, @PathVariable Integer id) {
        return storeService.getStoreById(principal.getName(), id);
    }

    @GetMapping("admin/owned")
    public List<StoreDTO> getStoresByOwner(Principal principal) {
        return storeService.getStoresByOwner(principal.getName());
    }

    @GetMapping("admin/{id}/export")
    public StoreLayoutTransferDTO exportStore(Principal principal, @PathVariable Integer id) {
        return storeService.exportStore(principal.getName(), id);
    }

    @PostMapping("admin")
    public void addNewStore(@RequestBody NewStoreDTO newStoreDTO, Principal principal) {
        storeService.addStore(principal.getName(), newStoreDTO);
    }

    @PostMapping("admin/{id}/import")
    public void importStore(Principal principal, @PathVariable Integer id, @RequestBody StoreLayoutTransferDTO storeLayoutTransferDTO) {
        storeService.importStore(principal.getName(), id, storeLayoutTransferDTO);
    }

    @DeleteMapping("admin/{id}")
    public void deleteStore(Principal principal, @PathVariable Integer id) {
        storeService.deleteStore(principal.getName(), id);
    }

    @PutMapping("admin/{id}/name")
    public void updateStoreName(Principal principal, @PathVariable Integer id, @RequestBody NameChangeDTO nameChangeDTO) {
        storeService.updateStoreName(principal.getName(), id, nameChangeDTO);
    }

    @PutMapping("admin/{id}/location")
    public void updateStoreLocation(Principal principal, @PathVariable Integer id, @RequestBody StoreLocationChangeDTO storeLocationChangeDTO) {
        storeService.updateStoreLocation(principal.getName(), id, storeLocationChangeDTO);
    }

    @PutMapping("admin/{id}/aisles")
    public void updateStoreAisles(Principal principal, @PathVariable Integer id, @ModelAttribute StoresAislesChangeDTO storesAislesChangeDTO) {
        storeService.updateStoreAisles(principal.getName(), id, storesAislesChangeDTO);
    }

    @PutMapping("admin/{id}/owners")
    public void updateStoreOwners(Principal principal, @PathVariable Integer id, @RequestBody StoreOwnersChangeDTO storeOwnersChangeDTO) {
        storeService.updateStoreOwners(principal.getName(), id, storeOwnersChangeDTO);
    }
}
