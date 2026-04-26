package com.groceryrun.app.controller;

import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.aisle.NewAisleDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;
import com.groceryrun.app.services.AisleService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller for aisles
 */
@RestController
@RequestMapping("api/v1/aisles")
public class AisleController {

    private final AisleService aisleService; // Service for aisle data

    /**
     * Constructor for aisle controller
     * @param aisleService service for aisle data
     */
    public AisleController(AisleService aisleService) {
        this.aisleService = aisleService;
    }

    /**
     * Retrieves all aisles from the database
     * @return list of aisle DTOs
     */
    @GetMapping
    public List<AisleDTO> getAllAisles() {
        return aisleService.getAllAisles();
    }

    /**
     * Retrieves an aisle by its id
     * @param id id of the aisle
     * @return aisle DTO
     */
    @GetMapping("{id}")
    public AisleDTO getAisle(@PathVariable Integer id) {
        return aisleService.getAisleById(id);
    }

    /**
     * Adds an aisle to a store
     * @param principal principal of the user
     * @param storeId id of the store
     * @param newAisleDTO new aisle data
     */
    @PostMapping("admin/{storeId}/aisles")
    public void addAisle(Principal principal, @PathVariable Integer storeId, @RequestBody NewAisleDTO newAisleDTO) {
        aisleService.addAisle(principal.getName(), storeId, newAisleDTO);
    }

    /**
     * Deletes an aisle from a store
     * @param principal principal of the user
     * @param aisleId id of the aisle
     * @param storeId id of the store
     */
    @DeleteMapping("admin/{storeId}/aisles/{aisleId}")
    public void deleteAisle(Principal principal, @PathVariable Integer aisleId, @PathVariable Integer storeId) {
        aisleService.deleteAisle(principal.getName(), aisleId, storeId);
    }

    /**
     * Updates the label of an aisle
     * @param principal principal of the user
     * @param id id of the aisle
     * @param labelChangeDTO new label
     */
    @PutMapping("admin/{id}/label")
    public void updateAisleLabel(Principal principal, @PathVariable Integer id, @RequestBody LabelChangeDTO labelChangeDTO) {
        aisleService.updateAisleLabel(principal.getName(), id, labelChangeDTO);
    }
}
