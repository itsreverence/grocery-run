package com.groceryrun.app.controller;

import com.groceryrun.app.dto.category.NewCategoryDTO;
import com.groceryrun.app.services.CategoryService;
import com.groceryrun.app.dto.category.CategoryDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller for categories
 */
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService; // Service for category data

    /**
     * Constructor for category controller
     * @param categoryService service for category data
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Retrieves all categories from the database
     * @return list of category DTOs
     */
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Retrieves a category by its id
     * @param id id of the category
     * @return category DTO
     */
    @GetMapping("{id}")
    public CategoryDTO getCategory(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    /**
     * Adds a category to an aisle
     * @param principal principal of the user
     * @param aisleId id of the aisle
     * @param newCategoryDTO new category data
     */
    @PostMapping("admin/{aisleId}/categories")
    public void addCategory(Principal principal, @PathVariable Integer aisleId, @RequestBody NewCategoryDTO newCategoryDTO) {
        categoryService.addCategory(principal.getName(), aisleId, newCategoryDTO);
    }

    /**
     * Deletes a category from an aisle
     * @param principal principal of the user
     * @param aisleId id of the aisle
     * @param categoryId id of the category
     */
    @DeleteMapping("admin/{aisleId}/categories/{categoryId}")
    public void deleteCategory(Principal principal, @PathVariable Integer aisleId, @PathVariable Integer categoryId) {
        categoryService.deleteCategory(principal.getName(), categoryId, aisleId);
    }

    /**
     * Updates the label of a category
     * @param principal principal of the user
     * @param id id of the category
     * @param labelChangeDTO new label
     */
    @PutMapping("admin/{id}/label")
    public void updateCategoryLabel(Principal principal, @PathVariable Integer id, @RequestBody LabelChangeDTO labelChangeDTO) {
        categoryService.updateCategoryLabel(principal.getName(), id, labelChangeDTO);
    }
}
