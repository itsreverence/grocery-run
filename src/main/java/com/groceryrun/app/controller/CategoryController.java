package com.groceryrun.app.controller;

import com.groceryrun.app.dto.category.CategoryLabelChangeDTO;
import com.groceryrun.app.dto.category.NewCategoryDTO;
import com.groceryrun.app.services.CategoryService;
import com.groceryrun.app.dto.category.CategoryAisleChangeDTO;
import com.groceryrun.app.dto.category.CategoryDTO;
import com.groceryrun.app.dto.category.CategoryItemsChangeDTO;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("{id}")
    public CategoryDTO getCategory(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public void addCategory(@ModelAttribute NewCategoryDTO newCategoryDTO) {
        categoryService.addCategory(newCategoryDTO);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }

    @PutMapping("{id}/label")
    public void updateCategoryLabel(@PathVariable Integer id, @ModelAttribute CategoryLabelChangeDTO categoryLabelChangeDTO) {
        categoryService.updateCategoryLabel(id, categoryLabelChangeDTO);
    }

    @PutMapping("{id}/aisle")
    public void updateCategoryAisle(@PathVariable Integer id, @ModelAttribute CategoryAisleChangeDTO categoryAisleChangeDTO) {
        categoryService.updateCategoryAisle(id, categoryAisleChangeDTO);
    }

    @PutMapping("{id}/items")
    public void updateCategoryItems(@PathVariable Integer id, @ModelAttribute CategoryItemsChangeDTO categoryItemsChangeDTO) {
        categoryService.updateCategoryItems(id, categoryItemsChangeDTO);
    }
}
