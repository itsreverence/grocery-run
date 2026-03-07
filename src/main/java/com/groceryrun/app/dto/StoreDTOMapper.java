package com.groceryrun.app.dto;

import com.groceryrun.app.entities.Store;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StoreDTOMapper implements Function<Store, StoreDTO> {
    @Override
    public StoreDTO apply(Store store) {
        return new StoreDTO(store.getId(), store.getName());
    }
}
