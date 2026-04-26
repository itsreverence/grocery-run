package com.groceryrun.app.repositories;

import com.groceryrun.app.entities.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for grocery list data
 */
public interface GroceryListRepository extends JpaRepository<GroceryList, Integer> {
}
