package com.groceryrun.app.controller;

import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.aisle.AisleLabelChangeDTO;
import com.groceryrun.app.dto.aisle.NewAisleDTO;
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

    @PostMapping
    public void addAisle(@ModelAttribute NewAisleDTO newAisleDTO) {
        aisleService.addAisle(newAisleDTO);
    }

    @DeleteMapping("{id}")
    public void deleteAisle(@PathVariable Integer id) {
        aisleService.deleteAisle(id);
    }

    @PutMapping("{id}/label")
    public void updateAisleLabel(@PathVariable Integer id, @ModelAttribute AisleLabelChangeDTO aisleLabelChangeDTO) {
        aisleService.updateAisleLabel(id, aisleLabelChangeDTO);
    }
}
