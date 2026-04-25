package com.groceryrun.app.repositories;

import com.groceryrun.app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for category data
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
