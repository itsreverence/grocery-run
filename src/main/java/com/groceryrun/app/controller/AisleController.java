package com.groceryrun.app.controller;

import com.groceryrun.app.dto.aisle.AisleCategoriesChangeDTO;
import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.aisle.AisleStoreChangeDTO;
import com.groceryrun.app.dto.category.NewCategoryDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;
import com.groceryrun.app.services.AisleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/aisles")
public class AisleController {

    private final AisleService aisleService;

    public AisleController(AisleService aisleService) {
        this.aisleService = aisleService;
    }

    @GetMapping
    public List<AisleDTO> getAllAisles() {
        return aisleService.getAllAisles();
    }

    @GetMapping("{id}")
    public AisleDTO getAisle(@PathVariable Integer id) {
        return aisleService.getAisleById(id);
    }

    @PostMapping("admin/{id}/categories")
    public void addCategoryToAisle(@PathVariable Integer id, @RequestBody NewCategoryDTO newCategoryDTO) {
        aisleService.addCategoryToAisle(id, newCategoryDTO);
    }

    @DeleteMapping("admin/{id}/categories/{categoryId}")
    public void deleteCategoryFromAisle(@PathVariable Integer id, @PathVariable Integer categoryId) {
        aisleService.deleteCategoryFromAisle(id, categoryId);
    }

    @PutMapping("{id}/label")
    public void updateAisleLabel(@PathVariable Integer id, @ModelAttribute LabelChangeDTO labelChangeDTO) {
        aisleService.updateAisleLabel(id, labelChangeDTO);
    }

    @PutMapping("{id}/store")
    public void updateAisleStore(@PathVariable Integer id, @ModelAttribute AisleStoreChangeDTO aisleStoreChangeDTO) {
        aisleService.updateAisleStore(id, aisleStoreChangeDTO);
    }

    @PutMapping("{id}/categories")
    public void updateAisleCategories(@PathVariable Integer id, @ModelAttribute AisleCategoriesChangeDTO aisleCategoriesChangeDTO) {
        aisleService.updateAisleCategories(id, aisleCategoriesChangeDTO);
    }
}
