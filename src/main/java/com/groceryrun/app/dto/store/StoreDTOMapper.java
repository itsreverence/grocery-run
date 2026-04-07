package com.groceryrun.app.dto.store;

import com.groceryrun.app.dto.user.UserDTOMapper;
import com.groceryrun.app.dto.aisle.AisleDTOMapper;
import com.groceryrun.app.dto.location.LocationDTOMapper;
import com.groceryrun.app.entities.Store;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StoreDTOMapper implements Function<Store, StoreDTO> {
    private final LocationDTOMapper locationDTOMapper;
    private final UserDTOMapper userDTOMapper;
    private final AisleDTOMapper aisleDTOMapper;

    public StoreDTOMapper(LocationDTOMapper locationDTOMapper, UserDTOMapper userDTOMapper, AisleDTOMapper aisleDTOMapper) {
        this.locationDTOMapper = locationDTOMapper;
        this.userDTOMapper = userDTOMapper;
        this.aisleDTOMapper = aisleDTOMapper;
    }
    @Override
    public StoreDTO apply(Store store) {
        return new StoreDTO(store.getId(), store.getName(), locationDTOMapper.apply(store.getLocation()), store.getOwners().stream().map(userDTOMapper).collect(Collectors.toList()), store.getAisles().stream().map(aisleDTOMapper).collect(Collectors.toList()));
    }
}
