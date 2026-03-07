package com.groceryrun.app.services;

import com.groceryrun.app.dto.location.*;
import com.groceryrun.app.entities.Location;
import com.groceryrun.app.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationDTOMapper locationDTOMapper;

    public LocationService(LocationRepository locationRepository, LocationDTOMapper locationDTOMapper) {
        this.locationRepository = locationRepository;
        this.locationDTOMapper = locationDTOMapper;
    }

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream().map(locationDTOMapper).collect(Collectors.toList());
    }

    public void addLocation(NewLocationDTO newLocationDTO) {
        Location location = new Location(newLocationDTO.state(), newLocationDTO.city(), newLocationDTO.state(), newLocationDTO.zip());
        locationRepository.save(location);
    }

    public LocationDTO getLocationById(Integer id) {
        return locationRepository.findById(id).map(locationDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void deleteLocation(Integer id) {
        boolean exists = locationRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        locationRepository.deleteById(id);
    }

    public void updateLocationStreet(Integer id, LocationStreetChangeDTO locationStreetChangeDTO) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        location.setStreet(locationStreetChangeDTO.newStreet());
        locationRepository.save(location);
    }

    public void updateLocationCity(Integer id, LocationCityChangeDTO locationCityChangeDTO) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        location.setCity(locationCityChangeDTO.newCity());
        locationRepository.save(location);
    }

    public void updateLocationState(Integer id, LocationStateChangeDTO locationStateChangeDTO) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        location.setState(locationStateChangeDTO.newState());
        locationRepository.save(location);
    }

    public void updateLocationZip(Integer id, LocationZipChangeDTO locationZipChangeDTO) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        location.setZip(locationZipChangeDTO.newZip());
        locationRepository.save(location);
    }
}
