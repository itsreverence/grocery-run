package com.groceryrun.app.repositories;

import com.groceryrun.app.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {

}
