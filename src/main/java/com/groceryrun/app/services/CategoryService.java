package com.groceryrun.app.services;

import com.groceryrun.app.dto.category.CategoryAisleChangeDTO;
import com.groceryrun.app.dto.category.CategoryDTO;
import com.groceryrun.app.dto.category.CategoryDTOMapper;
import com.groceryrun.app.dto.category.CategoryItemsChangeDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;
import com.groceryrun.app.dto.category.NewCategoryDTO;
import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.Category;
import com.groceryrun.app.repositories.CategoryRepository;
import com.groceryrun.app.repositories.AisleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final AisleRepository aisleRepository;
    private final CategoryDTOMapper categoryDTOMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryDTOMapper categoryDTOMapper, AisleRepository aisleRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryDTOMapper = categoryDTOMapper;
        this.aisleRepository = aisleRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryDTOMapper).collect(Collectors.toList());
    }

    public void addCategory(Integer aisleId, NewCategoryDTO newCategoryDTO) {
        Aisle aisle = aisleRepository.findById(aisleId).orElseThrow(() -> new IllegalStateException(aisleId + " not found"));
        Category category = new Category(newCategoryDTO.label(), aisle);
        categoryRepository.save(category);
        aisle.getCategories().add(category);
        aisleRepository.save(aisle);
    }

    public CategoryDTO getCategoryById(Integer id) {
        return categoryRepository.findById(id).map(categoryDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void deleteCategory(Integer id, Integer aisleId) {
        Aisle aisle = aisleRepository.findById(aisleId).orElseThrow(() -> new IllegalStateException(aisleId + " not found"));
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (aisle.getCategories().contains(category)) {
            aisle.getCategories().remove(category);
            aisleRepository.save(aisle);
            categoryRepository.delete(category);
        }
    }

    public void updateCategoryLabel(Integer id, LabelChangeDTO labelChangeDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        category.setLabel(labelChangeDTO.newLabel());
        categoryRepository.save(category);
    }

    public void updateCategoryAisle(Integer id, CategoryAisleChangeDTO categoryAisleChangeDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        category.setAisle(categoryAisleChangeDTO.newCategoryAisle());
        categoryRepository.save(category);
    }

    public void updateCategoryItems(Integer id, CategoryItemsChangeDTO categoryItemsChangeDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        category.setItems(categoryItemsChangeDTO.newCategoryItems());
        categoryRepository.save(category);
    }
}
