package com.groceryrun.app.services;

import com.groceryrun.app.dto.location.*;
import com.groceryrun.app.entities.Location;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.repositories.LocationRepository;
import com.groceryrun.app.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Location service which contains the business logic for retrieving, adding, deleting, and updating locations
 */
@Service
public class LocationService {

    private final LocationRepository locationRepository; // Repository for location data
    private final StoreRepository storeRepository; // Repository for store data
    private final LocationDTOMapper locationDTOMapper; // Mapper for location data

    /**
     * Constructor for location service
     * @param locationRepository repository for location data
     * @param locationDTOMapper mapper for location data
     * @param storeRepository repository for store data
     */
    public LocationService(LocationRepository locationRepository, LocationDTOMapper locationDTOMapper, StoreRepository storeRepository) {
        this.locationRepository = locationRepository;
        this.storeRepository = storeRepository;
        this.locationDTOMapper = locationDTOMapper;
    }

    /**
     * Retrieves all locations from the database
     * @return list of location DTOs
     */
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream().map(locationDTOMapper).collect(Collectors.toList());
    }

    /**
     * Adds a location to a store
     * @param username username of the user
     * @param newLocationDTO new location data
     * @throws IllegalStateException if the store is not found or the user is not an owner of the store
     */
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

    /**
     * Retrieves a location by its id
     * @param id id of the location
     * @return location DTO
     * @throws IllegalStateException if the location is not found
     */
    public LocationDTO getLocationById(Integer id) {
        return locationRepository.findById(id).map(locationDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    /**
     * Deletes a location
     * @param username username of the user
     * @param id id of the location
     * @throws IllegalStateException if the location is not found or, when the location belongs to a store, the user is not an owner of that store
     */
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

    /**
     * Updates the street of a location
     * @param username username of the user
     * @param id id of the location
     * @param locationStreetChangeDTO new street
     * @throws IllegalStateException if the location is not found or, when the location belongs to a store, the user is not an owner of that store
     */
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

    /**
     * Updates the city of a location
     * @param username username of the user
     * @param id id of the location
     * @param locationCityChangeDTO new city
     * @throws IllegalStateException if the location is not found or, when the location belongs to a store, the user is not an owner of that store
     */
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

    /**
     * Updates the state of a location
     * @param username username of the user
     * @param id id of the location
     * @param locationStateChangeDTO new state
     * @throws IllegalStateException if the location is not found or, when the location belongs to a store, the user is not an owner of that store
     */
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

    /**
     * Updates the zip of a location
     * @param username username of the user
     * @param id id of the location
     * @param locationZipChangeDTO new zip
     * @throws IllegalStateException if the location is not found or, when the location belongs to a store, the user is not an owner of that store
     */
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

    /**
     * Updates the store of a location
     * @param username username of the user
     * @param id id of the location
     * @param locationStoreChangeDTO new store
     * @throws IllegalStateException if the location is not found, if the user is not an owner of the current store when one exists, or if the user is not an owner of the new store
     */
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
