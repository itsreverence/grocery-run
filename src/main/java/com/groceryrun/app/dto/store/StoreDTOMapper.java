package com.groceryrun.app.dto.store;

import com.groceryrun.app.dto.user.UserDTOMapper;
import com.groceryrun.app.dto.aisle.AisleDTOMapper;
import com.groceryrun.app.dto.location.LocationDTOMapper;
import com.groceryrun.app.entities.Store;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mapper for store data
 */
@Service
public class StoreDTOMapper implements Function<Store, StoreDTO> {
    private final LocationDTOMapper locationDTOMapper; // Mapper for location data
    private final UserDTOMapper userDTOMapper; // Mapper for user data
    private final AisleDTOMapper aisleDTOMapper; // Mapper for aisle data

    /**
     * Constructor for store DTO mapper
     * @param locationDTOMapper mapper for location data
     * @param userDTOMapper mapper for user data
     * @param aisleDTOMapper mapper for aisle data
     */
    public StoreDTOMapper(LocationDTOMapper locationDTOMapper, UserDTOMapper userDTOMapper, AisleDTOMapper aisleDTOMapper) {
        this.locationDTOMapper = locationDTOMapper;
        this.userDTOMapper = userDTOMapper;
        this.aisleDTOMapper = aisleDTOMapper;
    }

    /**
     * Maps a store entity to a store DTO
     * @param store store entity
     * @return store DTO
     */
    @Override
    public StoreDTO apply(Store store) {
        return new StoreDTO(store.getId(), store.getName(), locationDTOMapper.apply(store.getLocation()), store.getOwners().stream().map(userDTOMapper).collect(Collectors.toList()), store.getAisles().stream().map(aisleDTOMapper).collect(Collectors.toList()));
    }
}
