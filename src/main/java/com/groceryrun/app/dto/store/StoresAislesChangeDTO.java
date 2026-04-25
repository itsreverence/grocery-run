package com.groceryrun.app.dto.store;

import java.util.List;

import com.groceryrun.app.entities.Aisle;

/**
 * DTO for a store aisles change
 * @param newAisles new aisles
 */
public record StoresAislesChangeDTO(List<Aisle> newAisles) {
    
}
