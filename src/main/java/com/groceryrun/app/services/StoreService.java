package com.groceryrun.app.services;

import com.groceryrun.app.entities.Store;
import com.groceryrun.app.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public void addStore(Store store) {
        storeRepository.save(store);
    }

    public Store getStoreById(Integer id) {
        return storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void deleteStore(Integer id) {
        boolean exists =  storeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        storeRepository.deleteById(id);
    }

    public void updateStoreName(Integer id, String name) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        store.setName(name);
        storeRepository.save(store);
    }
}
