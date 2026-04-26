package com.groceryrun.app.services;

import com.groceryrun.app.dto.category.CategoryDTO;
import com.groceryrun.app.dto.category.CategoryDTOMapper;
import com.groceryrun.app.dto.shared.LabelChangeDTO;
import com.groceryrun.app.dto.category.NewCategoryDTO;
import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.Category;
import com.groceryrun.app.repositories.CategoryRepository;
import com.groceryrun.app.repositories.AisleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Category service which contains the business logic for retrieving, adding, deleting, and updating categories
 */
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository; // Repository for category data
    private final AisleRepository aisleRepository; // Repository for aisle data
    private final CategoryDTOMapper categoryDTOMapper; // Mapper for category data

    /**
     * Constructor for category service
     * @param categoryRepository repository for category data
     * @param categoryDTOMapper mapper for category data
     * @param aisleRepository repository for aisle data
     */
    public CategoryService(CategoryRepository categoryRepository, CategoryDTOMapper categoryDTOMapper, AisleRepository aisleRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryDTOMapper = categoryDTOMapper;
        this.aisleRepository = aisleRepository;
    }

    /**
     * Retrieves all categories from the database
     * @return list of category DTOs
     */
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryDTOMapper).collect(Collectors.toList());
    }

    /**
     * Adds a category to an aisle
     * @param username username of the user
     * @param aisleId id of the aisle
     * @param newCategoryDTO new category data
     * @throws IllegalStateException if the aisle is not found or the user is not an owner of the store containing the aisle
     */
    public void addCategory(String username, Integer aisleId, NewCategoryDTO newCategoryDTO) {
        Aisle aisle = aisleRepository.findById(aisleId).orElseThrow(() -> new IllegalStateException(aisleId + " not found"));
        if (!aisle.getStore().getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of aisle " + aisleId);
        }
        Category category = new Category(newCategoryDTO.label(), aisle);
        categoryRepository.save(category);
        aisle.getCategories().add(category);
        aisleRepository.save(aisle);
    }

    /**
     * Retrieves a category by its id
     * @param id id of the category
     * @return category DTO
     * @throws IllegalStateException if the category is not found
     */
    public CategoryDTO getCategoryById(Integer id) {
        return categoryRepository.findById(id).map(categoryDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    /**
     * Deletes a category from an aisle
     * @param username username of the user
     * @param id id of the category
     * @param aisleId id of the aisle
     * @throws IllegalStateException if the aisle is not found or the user is not an owner of the store containing the aisle
     */
    public void deleteCategory(String username, Integer id, Integer aisleId) {
        Aisle aisle = aisleRepository.findById(aisleId).orElseThrow(() -> new IllegalStateException(aisleId + " not found"));
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!aisle.getStore().getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of aisle " + aisleId);
        }
        if (aisle.getCategories().contains(category)) {
            aisle.getCategories().remove(category);
            aisleRepository.save(aisle);
            categoryRepository.delete(category);
        }
    }

    /**
     * Updates the label of a category
     * @param username username of the user
     * @param id id of the category
     * @param labelChangeDTO new label
     * @throws IllegalStateException if the category is not found or the user is not an owner of the store containing the category
     */
    public void updateCategoryLabel(String username, Integer id, LabelChangeDTO labelChangeDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!category.getAisle().getStore().getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of category " + id);
        }
        category.setLabel(labelChangeDTO.newLabel());
        categoryRepository.save(category);
    }
}
