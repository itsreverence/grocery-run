package com.groceryrun.app.controller;

import com.groceryrun.app.dto.category.NewCategoryDTO;
import com.groceryrun.app.dto.item.NewItemDTO;
import com.groceryrun.app.services.CategoryService;
import com.groceryrun.app.dto.category.CategoryAisleChangeDTO;
import com.groceryrun.app.dto.category.CategoryDTO;
import com.groceryrun.app.dto.category.CategoryItemsChangeDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;

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

    @PostMapping("admin/{aisleId}/categories")
    public void addCategory(@PathVariable Integer aisleId, @RequestBody NewCategoryDTO newCategoryDTO) {
        categoryService.addCategory(aisleId, newCategoryDTO);
    }

    @DeleteMapping("admin/{aisleId}/categories/{categoryId}")
    public void deleteCategory(@PathVariable Integer aisleId, @PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId, aisleId);
    }

    @PutMapping("{id}/label")
    public void updateCategoryLabel(@PathVariable Integer id, @RequestBody LabelChangeDTO labelChangeDTO) {
        categoryService.updateCategoryLabel(id, labelChangeDTO);
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
