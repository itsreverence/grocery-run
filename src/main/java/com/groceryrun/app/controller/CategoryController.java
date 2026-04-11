package com.groceryrun.app.controller;

import com.groceryrun.app.dto.category.NewCategoryDTO;
import com.groceryrun.app.services.CategoryService;
import com.groceryrun.app.dto.category.CategoryDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public void addCategory(Principal principal, @PathVariable Integer aisleId, @RequestBody NewCategoryDTO newCategoryDTO) {
        categoryService.addCategory(principal.getName(), aisleId, newCategoryDTO);
    }

    @DeleteMapping("admin/{aisleId}/categories/{categoryId}")
    public void deleteCategory(Principal principal, @PathVariable Integer aisleId, @PathVariable Integer categoryId) {
        categoryService.deleteCategory(principal.getName(), categoryId, aisleId);
    }

    @PutMapping("admin/{id}/label")
    public void updateCategoryLabel(Principal principal, @PathVariable Integer id, @RequestBody LabelChangeDTO labelChangeDTO) {
        categoryService.updateCategoryLabel(principal.getName(), id, labelChangeDTO);
    }
}
