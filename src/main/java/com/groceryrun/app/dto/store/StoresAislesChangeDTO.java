package com.groceryrun.app.dto.store;

import java.util.List;

import com.groceryrun.app.entities.Aisle;

public record StoresAislesChangeDTO(List<Aisle> newAisles) {
    
}
