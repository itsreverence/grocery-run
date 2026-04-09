package com.groceryrun.app.services;

import com.groceryrun.app.dto.store.*;
import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.Location;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.entities.User;
import com.groceryrun.app.dto.aisle.NewAisleDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.repositories.AisleRepository;
import com.groceryrun.app.repositories.LocationRepository;
import com.groceryrun.app.repositories.StoreRepository;
import com.groceryrun.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final AisleRepository aisleRepository;
    private final StoreDTOMapper storeDTOMapper;

    public StoreService(StoreRepository storeRepository, StoreDTOMapper storeDTOMapper, UserRepository userRepository, LocationRepository locationRepository, AisleRepository aisleRepository) {
        this.storeRepository = storeRepository;
        this.storeDTOMapper = storeDTOMapper;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.aisleRepository = aisleRepository;
    }

    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream().map(storeDTOMapper).collect(Collectors.toList());
    }

    public void addStore(String username, NewStoreDTO newStoreDTO) {
        List<User> owners = new ArrayList<>();
        User owner = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        owners.add(owner);
        Location location = new Location(newStoreDTO.storeLocation().street(), newStoreDTO.storeLocation().city(), newStoreDTO.storeLocation().state(), newStoreDTO.storeLocation().zip(), null);
        locationRepository.save(location);
        Store store = new Store(newStoreDTO.storeName(), location, owners);
        storeRepository.save(store);
        location.setStore(store);
        locationRepository.save(location);
        owner.getStores().add(store);
        userRepository.save(owner);
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

    public void updateStoreName(Integer id, NameChangeDTO nameChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        store.setName(nameChangeDTO.newName());
        storeRepository.save(store);
    }

    public void updateStoreLocation(Integer id, StoreLocationChangeDTO storeLocationChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        Location location = store.getLocation();
        location.setStreet(storeLocationChangeDTO.newLocationDTO().street());
        location.setCity(storeLocationChangeDTO.newLocationDTO().city());
        location.setState(storeLocationChangeDTO.newLocationDTO().state());
        location.setZip(storeLocationChangeDTO.newLocationDTO().zip());
        locationRepository.save(location);
        store.setLocation(location);
        storeRepository.save(store);
    }

    public void updateStoreAisles(Integer id, StoresAislesChangeDTO storesAislesChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        store.setAisles(storesAislesChangeDTO.newAisles());
        storeRepository.save(store);
    }

    public void updateStoreOwners(Integer id, StoreOwnersChangeDTO storeOwnersChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        List<User> oldOwners = new ArrayList<>(store.getOwners());
        List<User> newOwners = new ArrayList<>();
        for (Integer ownerId : storeOwnersChangeDTO.newOwnerIds()) {
            User owner = userRepository.findById(ownerId).orElseThrow(() -> new IllegalStateException(ownerId + " not found"));
            newOwners.add(owner);
        }
        store.setOwners(newOwners);
        storeRepository.save(store);
        for (User owner : oldOwners) {
            if (owner.getStores().contains(store)) {
                owner.getStores().remove(store);
                userRepository.save(owner);
            }
        }
        for (User owner : newOwners) {
            if (!owner.getStores().contains(store)) {
                owner.getStores().add(store);
                userRepository.save(owner);
            }
        }
    }

    public List<StoreDTO> getStoresByOwner(String username) {
       List<Store> stores = storeRepository.findAll().stream().collect(Collectors.toList());
       List<Store> ownedStores = new ArrayList<>();
       for (Store store : stores) {
        List<User> owners = store.getOwners();
        for (User owner : owners) {
            if (owner.getUsername().equals(username)) {
                ownedStores.add(store);
            }
        }
       }

       return ownedStores.stream().map(storeDTOMapper).collect(Collectors.toList());


    }
}
