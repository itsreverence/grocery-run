package com.groceryrun.app.dto.category;

import com.groceryrun.app.entities.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {
    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(category.getId(), category.getLabel(), category.getCategoryAisle());
    }
}
