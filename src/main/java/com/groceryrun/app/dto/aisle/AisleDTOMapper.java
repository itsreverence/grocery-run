package com.groceryrun.app.dto.aisle;

import com.groceryrun.app.entities.Aisle;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AisleDTOMapper implements Function<Aisle, AisleDTO> {
    @Override
    public AisleDTO apply(Aisle aisle) {
        return new AisleDTO(aisle.getId(), aisle.getLabel(), aisle.getStore(), aisle.getCategories());
    }
}
