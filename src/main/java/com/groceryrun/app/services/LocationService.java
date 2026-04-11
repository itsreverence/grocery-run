package com.groceryrun.app.services;

import com.groceryrun.app.dto.location.*;
import com.groceryrun.app.entities.Location;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.repositories.LocationRepository;
import com.groceryrun.app.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final StoreRepository storeRepository;
    private final LocationDTOMapper locationDTOMapper;

    public LocationService(LocationRepository locationRepository, LocationDTOMapper locationDTOMapper, StoreRepository storeRepository) {
        this.locationRepository = locationRepository;
        this.storeRepository = storeRepository;
        this.locationDTOMapper = locationDTOMapper;
    }

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream().map(locationDTOMapper).collect(Collectors.toList());
    }

    public void addLocation(String username, NewLocationDTO newLocationDTO) {
        Store store = storeRepository.findById(newLocationDTO.store().getId()).orElseThrow(() -> new IllegalStateException(newLocationDTO.store().getId() + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + newLocationDTO.store().getId());
        }
        Location location = new Location(newLocationDTO.street(), newLocationDTO.city(), newLocationDTO.state(), newLocationDTO.zip(), newLocationDTO.store());
        locationRepository.save(location);
        store.setLocation(location);
        storeRepository.save(store);
    }

    public LocationDTO getLocationById(Integer id) {
        return locationRepository.findById(id).map(locationDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void deleteLocation(String username, Integer id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (location.getStore() != null) {
            Store store = location.getStore();
            if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
                throw new IllegalStateException("User " + username + " is not an owner of store " + store.getId());
            }
        }
        boolean exists = locationRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        locationRepository.deleteById(id);
    }

    public void updateLocationStreet(String username, Integer id, LocationStreetChangeDTO locationStreetChangeDTO) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (location.getStore() != null) {
            Store store = location.getStore();
            if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
                throw new IllegalStateException("User " + username + " is not an owner of store " + store.getId());
            }
        }
        location.setStreet(locationStreetChangeDTO.newStreet());
        locationRepository.save(location);
    }

    public void updateLocationCity(String username, Integer id, LocationCityChangeDTO locationCityChangeDTO) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (location.getStore() != null) {
            Store store = location.getStore();
            if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
                throw new IllegalStateException("User " + username + " is not an owner of store " + store.getId());
            }
        }
        location.setCity(locationCityChangeDTO.newCity());
        locationRepository.save(location);
    }

    public void updateLocationState(String username, Integer id, LocationStateChangeDTO locationStateChangeDTO) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (location.getStore() != null) {
            Store store = location.getStore();
            if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
                throw new IllegalStateException("User " + username + " is not an owner of store " + store.getId());
            }
        }
        location.setState(locationStateChangeDTO.newState());
        locationRepository.save(location);
    }

    public void updateLocationZip(String username, Integer id, LocationZipChangeDTO locationZipChangeDTO) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (location.getStore() != null) {
            Store store = location.getStore();
            if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
                throw new IllegalStateException("User " + username + " is not an owner of store " + store.getId());
            }
        }
        location.setZip(locationZipChangeDTO.newZip());
        locationRepository.save(location);
    }

    public void updateLocationStore(String username, Integer id, LocationStoreChangeDTO locationStoreChangeDTO) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (location.getStore() != null) {
            Store store = location.getStore();
            if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
                throw new IllegalStateException("User " + username + " is not an owner of store " + store.getId());
            }
        }
        Store newStore = storeRepository.findById(locationStoreChangeDTO.newStore().getId()).orElseThrow(() -> new IllegalStateException(locationStoreChangeDTO.newStore().getId() + " not found"));
        if (!newStore.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + locationStoreChangeDTO.newStore().getId());
        }
        location.setStore(newStore);
        locationRepository.save(location);
        newStore.setLocation(location);
        storeRepository.save(newStore);
    }
}
