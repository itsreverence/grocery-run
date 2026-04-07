package com.groceryrun.app.dto.aisle;

import com.groceryrun.app.dto.category.CategoryDTOMapper;
import com.groceryrun.app.entities.Aisle;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AisleDTOMapper implements Function<Aisle, AisleDTO> {
    private final CategoryDTOMapper categoryDTOMapper;

    public AisleDTOMapper(CategoryDTOMapper categoryDTOMapper) {
        this.categoryDTOMapper = categoryDTOMapper;
    }
    
    @Override
    public AisleDTO apply(Aisle aisle) {
        return new AisleDTO(aisle.getId(), aisle.getLabel(), aisle.getCategories().stream().map(categoryDTOMapper).collect(Collectors.toList()));
    }
}
