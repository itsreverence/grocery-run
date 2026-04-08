package com.groceryrun.app.services;

import com.groceryrun.app.dto.aisle.AisleCategoriesChangeDTO;
import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.aisle.AisleDTOMapper;
import com.groceryrun.app.dto.aisle.AisleStoreChangeDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;
import com.groceryrun.app.dto.category.NewCategoryDTO;
import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.Category;
import com.groceryrun.app.repositories.AisleRepository;
import com.groceryrun.app.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AisleService {
    private final AisleRepository aisleRepository;
    private final CategoryRepository categoryRepository;
    private final AisleDTOMapper aisleDTOMapper;

    public AisleService(AisleRepository aisleRepository, AisleDTOMapper aisleDTOMapper, CategoryRepository categoryRepository) {
        this.aisleRepository = aisleRepository;
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

    public void deleteCategoryFromAisle(Integer id, Integer categoryId) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException(categoryId + " not found"));
        if (aisle.getCategories().contains(category)) {
            aisle.getCategories().remove(category);
            aisleRepository.save(aisle);
            categoryRepository.delete(category);
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
