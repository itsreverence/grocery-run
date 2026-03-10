package com.groceryrun.app.services;

import com.groceryrun.app.dto.store.*;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreDTOMapper storeDTOMapper;

    public StoreService(StoreRepository storeRepository, StoreDTOMapper storeDTOMapper) {
        this.storeRepository = storeRepository;
        this.storeDTOMapper = storeDTOMapper;
    }

    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream().map(storeDTOMapper).collect(Collectors.toList());
    }

    public void addStore(NewStoreDTO newStoreDTO) {
        Store store = new Store(newStoreDTO.storeName(), newStoreDTO.storeLocation());
        storeRepository.save(store);
    }

    public StoreDTO getStoreById(Integer id) {
        return storeRepository.findById(id).map(storeDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void deleteStore(Integer id) {
        boolean exists =  storeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        storeRepository.deleteById(id);
    }

    public void updateStoreName(Integer id, StoreNameChangeDTO storeNameChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        store.setName(storeNameChangeDTO.newName());
        storeRepository.save(store);
    }

    public void updateStoreGroceryLists(Integer id, StoreGroceryListsChangeDTO storeGroceryListsChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        store.setGroceryLists(storeGroceryListsChangeDTO.newGroceryLists());
        storeRepository.save(store);
    }

    public void updateStoreLocation(Integer id, StoreLocationChangeDTO storeLocationChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        store.setStoreLocation(storeLocationChangeDTO.newStoreLocation());
        storeRepository.save(store);
    }

    public void updateStoreAisles(Integer id, StoresAislesChangeDTO storesAislesChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        store.setAisles(storesAislesChangeDTO.newAisles());
        storeRepository.save(store);
    }
}
