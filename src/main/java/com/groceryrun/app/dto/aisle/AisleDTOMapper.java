package com.groceryrun.app.dto.aisle;

import com.groceryrun.app.dto.category.CategoryDTOMapper;
import com.groceryrun.app.entities.Aisle;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mapper for aisle data
 */
@Service
public class AisleDTOMapper implements Function<Aisle, AisleDTO> {
    private final CategoryDTOMapper categoryDTOMapper; // Mapper for category data

    /**
     * Constructor for aisle DTO mapper
     * @param categoryDTOMapper mapper for category data
     */
    public AisleDTOMapper(CategoryDTOMapper categoryDTOMapper) {
        this.categoryDTOMapper = categoryDTOMapper;
    }
    
    /**
     * Maps an aisle entity to an aisle DTO
     * @param aisle aisle entity
     * @return aisle DTO
     */
    @Override
    public AisleDTO apply(Aisle aisle) {
        return new AisleDTO(aisle.getId(), aisle.getLabel(), aisle.getCategories().stream().map(categoryDTOMapper).collect(Collectors.toList()));
    }
}
