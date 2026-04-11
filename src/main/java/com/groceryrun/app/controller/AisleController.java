package com.groceryrun.app.controller;

import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.aisle.NewAisleDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;
import com.groceryrun.app.services.AisleService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @PostMapping("admin/{storeId}/aisles")
    public void addAisle(Principal principal, @PathVariable Integer storeId, @RequestBody NewAisleDTO newAisleDTO) {
        aisleService.addAisle(principal.getName(), storeId, newAisleDTO);
    }

    @DeleteMapping("admin/{storeId}/aisles/{aisleId}")
    public void deleteAisle(Principal principal, @PathVariable Integer aisleId, @PathVariable Integer storeId) {
        aisleService.deleteAisle(principal.getName(), aisleId, storeId);
    }

    @PutMapping("admin/{id}/label")
    public void updateAisleLabel(Principal principal, @PathVariable Integer id, @RequestBody LabelChangeDTO labelChangeDTO) {
        aisleService.updateAisleLabel(principal.getName(), id, labelChangeDTO);
    }
}
