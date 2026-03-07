package com.groceryrun.app.repositories;

import com.groceryrun.app.entities.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryListRepository extends JpaRepository<GroceryList, Integer> {
}
