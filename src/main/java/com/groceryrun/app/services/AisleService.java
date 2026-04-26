package com.groceryrun.app.services;

import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.aisle.AisleDTOMapper;
import com.groceryrun.app.dto.aisle.NewAisleDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;
import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.repositories.AisleRepository;
import com.groceryrun.app.repositories.StoreRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Aisle service which contains the business logic for retrieving, adding, deleting, and updating aisles
 */
@Service
public class AisleService {
    private final AisleRepository aisleRepository; // Repository for aisle data
    private final StoreRepository storeRepository; // Repository for store data
    private final AisleDTOMapper aisleDTOMapper; // Mapper for aisle data

    /**
     * Constructor for aisle service
     * @param aisleRepository repository for aisle data
     * @param aisleDTOMapper mapper for aisle data
     * @param storeRepository repository for store data
     */
    public AisleService(AisleRepository aisleRepository, AisleDTOMapper aisleDTOMapper, StoreRepository storeRepository) {
        this.aisleRepository = aisleRepository;
        this.storeRepository = storeRepository;
        this.aisleDTOMapper = aisleDTOMapper;
    }

    /**
     * Retrieves all aisles from the database
     * @return list of aisle DTOs
     */
    public List<AisleDTO> getAllAisles() {
        return aisleRepository.findAll().stream().map(aisleDTOMapper).collect(Collectors.toList());
    }

    /**
     * Retrieves an aisle by its id
     * @param id id of the aisle
     * @return aisle DTO
     * @throws IllegalStateException if the aisle is not found
     */
    public AisleDTO getAisleById(Integer id) {
        return aisleRepository.findById(id).map(aisleDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    /**
     * Adds an aisle to a store
     * @param username username of the user
     * @param storeId id of the store
     * @param newAisleDTO new aisle data
     * @throws IllegalStateException if the store is not found or the user is not an owner of the store
     */
    public void addAisle(String username, Integer storeId, NewAisleDTO newAisleDTO) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalStateException(storeId + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + storeId);
        }
        Aisle aisle = new Aisle(newAisleDTO.label(), store);
        aisleRepository.save(aisle);
        store.getAisles().add(aisle);
        storeRepository.save(store);
    }

    /**
     * Deletes an aisle from a store
     * @param username username of the user
     * @param aisleId id of the aisle
     * @param storeId id of the store
     * @throws IllegalStateException if the aisle is not found or the user is not an owner of the store
     */
    public void deleteAisle(String username, Integer aisleId, Integer storeId) {
        Aisle aisle = aisleRepository.findById(aisleId).orElseThrow(() -> new IllegalStateException(aisleId + " not found"));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalStateException(storeId + " not found"));
        if (!store.getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of store " + storeId);
        }
        if (store.getAisles().contains(aisle)) {
            store.getAisles().remove(aisle);
            storeRepository.save(store);
            aisleRepository.delete(aisle);
        }
    }

    /**
     * Updates the label of an aisle
     * @param username username of the user
     * @param id id of the aisle
     * @param labelChangeDTO new label
     * @throws IllegalStateException if the aisle is not found or the user is not an owner of the store containing the aisle
     */
    public void updateAisleLabel(String username, Integer id, LabelChangeDTO labelChangeDTO) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!aisle.getStore().getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of aisle " + id);
        }
        aisle.setLabel(labelChangeDTO.newLabel());
        aisleRepository.save(aisle);
    }
}
