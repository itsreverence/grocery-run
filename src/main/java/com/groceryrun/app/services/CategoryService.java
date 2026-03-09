package com.groceryrun.app.services;

import com.groceryrun.app.dto.category.CategoryDTO;
import com.groceryrun.app.dto.category.CategoryDTOMapper;
import com.groceryrun.app.dto.category.CategoryLabelChangeDTO;
import com.groceryrun.app.dto.category.NewCategoryDTO;
import com.groceryrun.app.entities.Category;
import com.groceryrun.app.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDTOMapper categoryDTOMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryDTOMapper categoryDTOMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryDTOMapper = categoryDTOMapper;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryDTOMapper).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Integer id) {
        return categoryRepository.findById(id).map(categoryDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void addCategory(NewCategoryDTO newCategoryDTO) {
        Category category = new Category(newCategoryDTO.label());
        categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        boolean exists = categoryRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        categoryRepository.deleteById(id);
    }

    public void updateCategoryLabel(Integer id, CategoryLabelChangeDTO categoryLabelChangeDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        category.setLabel(categoryLabelChangeDTO.newLabel());
        categoryRepository.save(category);
    }
}
