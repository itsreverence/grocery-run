package com.groceryrun.app.dto.location;

import com.groceryrun.app.entities.Location;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper for location data
 */
@Service
public class LocationDTOMapper implements Function<Location, LocationDTO> {
    /**
     * Maps a location entity to a location DTO
     * @param location location entity
     * @return location DTO
     */
    @Override
    public LocationDTO apply(Location location) {
        return new LocationDTO(location.getId(), location.getStreet(), location.getCity(), location.getState(), location.getZip());
    }
}
