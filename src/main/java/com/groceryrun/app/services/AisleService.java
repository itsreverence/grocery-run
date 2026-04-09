package com.groceryrun.app.services;

import com.groceryrun.app.dto.aisle.AisleCategoriesChangeDTO;
import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.aisle.AisleDTOMapper;
import com.groceryrun.app.dto.aisle.AisleStoreChangeDTO;
import com.groceryrun.app.dto.aisle.NewAisleDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;
import com.groceryrun.app.dto.category.NewCategoryDTO;
import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.repositories.AisleRepository;
import com.groceryrun.app.repositories.StoreRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AisleService {
    private final AisleRepository aisleRepository;
    private final StoreRepository storeRepository;
    private final AisleDTOMapper aisleDTOMapper;

    public AisleService(AisleRepository aisleRepository, AisleDTOMapper aisleDTOMapper, StoreRepository storeRepository) {
        this.aisleRepository = aisleRepository;
        this.storeRepository = storeRepository;
        this.aisleDTOMapper = aisleDTOMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<AisleDTO> getAllAisles() {
        return aisleRepository.findAll().stream().map(aisleDTOMapper).collect(Collectors.toList());
    }

    public void addCategoryToAisle(Integer id, NewCategoryDTO newCategoryDTO) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        Category category = new Category(newCategoryDTO.label(), aisle);
        categoryRepository.save(category);
        aisle.getCategories().add(category);
        aisleRepository.save(aisle);
    }

    public AisleDTO getAisleById(Integer id) {
        return aisleRepository.findById(id).map(aisleDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void addAisle(Integer storeId, NewAisleDTO newAisleDTO) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalStateException(storeId + " not found"));
        Aisle aisle = new Aisle(newAisleDTO.label(), store);
        aisleRepository.save(aisle);
        store.getAisles().add(aisle);
        storeRepository.save(store);
    }

    public void deleteAisle(Integer aisleId, Integer storeId) {
        Aisle aisle = aisleRepository.findById(aisleId).orElseThrow(() -> new IllegalStateException(aisleId + " not found"));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalStateException(storeId + " not found"));
        if (store.getAisles().contains(aisle)) {
            store.getAisles().remove(aisle);
            storeRepository.save(store);
            aisleRepository.delete(aisle);
        }
    }

    public void updateAisleLabel(Integer id, LabelChangeDTO labelChangeDTO) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        aisle.setLabel(labelChangeDTO.newLabel());
        aisleRepository.save(aisle);
    }

    public void updateAisleStore(Integer id, AisleStoreChangeDTO aisleStoreChangeDTO) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        aisle.setStore(aisleStoreChangeDTO.newAisleStore());
        aisleRepository.save(aisle);
    }

    public void updateAisleCategories(Integer id, AisleCategoriesChangeDTO aisleCategoriesChangeDTO) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        aisle.setCategories(aisleCategoriesChangeDTO.newAisleCategories());
        aisleRepository.save(aisle);
    }
}
