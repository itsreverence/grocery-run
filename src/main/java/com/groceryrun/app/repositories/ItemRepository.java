package com.groceryrun.app.repositories;

import com.groceryrun.app.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
