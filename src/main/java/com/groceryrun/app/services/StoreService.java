package com.groceryrun.app.services;

import com.groceryrun.app.dto.store.*;
import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.Category;
import com.groceryrun.app.entities.Item;
import com.groceryrun.app.entities.Location;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.entities.User;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.repositories.AisleRepository;
import com.groceryrun.app.repositories.LocationRepository;
import com.groceryrun.app.repositories.StoreRepository;
import com.groceryrun.app.repositories.CategoryRepository;
import com.groceryrun.app.repositories.ItemRepository;
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
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final StoreDTOMapper storeDTOMapper;

    public StoreService(StoreRepository storeRepository, StoreDTOMapper storeDTOMapper, UserRepository userRepository, LocationRepository locationRepository, AisleRepository aisleRepository, CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.storeRepository = storeRepository;
        this.storeDTOMapper = storeDTOMapper;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.aisleRepository = aisleRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream().map(storeDTOMapper).collect(Collectors.toList());
    }

    public StoreLayoutTransferDTO exportStore(String username, Integer id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + id);
        }
        return new StoreLayoutTransferDTO(store.getName(), store.getAisles().stream().map(aisle -> new StoreLayoutAisleDTO(aisle.getLabel(), aisle.getCategories().stream().map(category -> new StoreLayoutCategoryDTO(category.getLabel(), category.getItems().stream().map(item -> new StoreLayoutItemDTO(item.getName())).collect(Collectors.toList()))).collect(Collectors.toList()))).collect(Collectors.toList()));
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

    public StoreDTO getStoreById(String username, Integer id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + id);
        }
        return storeRepository.findById(id).map(storeDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void importStore(String username, Integer id, StoreLayoutTransferDTO storeLayoutTransferDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + id);
        }
        for (Aisle aisle : new ArrayList<>(store.getAisles())) {
            aisleRepository.delete(aisle);
        }
        store.getAisles().clear();
        for (StoreLayoutAisleDTO aisleDTO : storeLayoutTransferDTO.aisles()) {
            Aisle aisle = new Aisle(aisleDTO.label(), store);
            aisleRepository.save(aisle);
            store.getAisles().add(aisle);
            for (StoreLayoutCategoryDTO categoryDTO : aisleDTO.categories()) {
                Category category = new Category(categoryDTO.label(), aisle);
                categoryRepository.save(category);
                aisle.getCategories().add(category);
                for (StoreLayoutItemDTO itemDTO : categoryDTO.items()) {
                    Item item = new Item(itemDTO.name(), category);
                    itemRepository.save(item);
                    category.getItems().add(item);
                }
            }
        }
        storeRepository.save(store);
    }
    

    public void deleteStore(String username, Integer id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + id);
        }

        for (User owner : new ArrayList<>(store.getOwners())) {
            owner.getStores().remove(store);
            userRepository.save(owner);
        }

        for (Aisle aisle : new ArrayList<>(store.getAisles())) {
            for (Category category : new ArrayList<>(aisle.getCategories())) {
                for (Item item : new ArrayList<>(category.getItems())) {
                    itemRepository.delete(item);
                }
                categoryRepository.delete(category);
            }
            aisleRepository.delete(aisle);
        }

        Location location = store.getLocation();
        storeRepository.delete(store);
        locationRepository.delete(location);
    }

    public void updateStoreName(String username, Integer id, NameChangeDTO nameChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + id);
        }
        store.setName(nameChangeDTO.newName());
        storeRepository.save(store);
    }

    public void updateStoreLocation(String username, Integer id, StoreLocationChangeDTO storeLocationChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + id);
        }
        Location location = store.getLocation();
        location.setStreet(storeLocationChangeDTO.newLocationDTO().street());
        location.setCity(storeLocationChangeDTO.newLocationDTO().city());
        location.setState(storeLocationChangeDTO.newLocationDTO().state());
        location.setZip(storeLocationChangeDTO.newLocationDTO().zip());
        locationRepository.save(location);
        store.setLocation(location);
        storeRepository.save(store);
    }

    public void updateStoreAisles(String username, Integer id, StoresAislesChangeDTO storesAislesChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + id);
        }
        store.setAisles(storesAislesChangeDTO.newAisles());
        storeRepository.save(store);
    }

    public void updateStoreOwners(String username, Integer id, StoreOwnersChangeDTO storeOwnersChangeDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + id);
        }
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
