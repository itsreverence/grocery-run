package com.groceryrun.app.controller;

import com.groceryrun.app.dto.location.*;
import com.groceryrun.app.services.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDTO> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("{id}")
    public LocationDTO getLocation(@PathVariable Integer id) {
        return locationService.getLocationById(id);
    }

    @PostMapping
    public void addNewLocation(@ModelAttribute NewLocationDTO newLocationDTO) {
        locationService.addLocation(newLocationDTO);
    }

    @DeleteMapping("{id}")
    public void deleteLocation(@PathVariable Integer id) {
        locationService.deleteLocation(id);
    }

    @PutMapping("{id}/street")
    public void updateLocationStreet(@PathVariable Integer id, @ModelAttribute LocationStreetChangeDTO locationStreetChangeDTO) {
        locationService.updateLocationStreet(id, locationStreetChangeDTO);
    }

    @PutMapping("{id}/city")
    public void updateLocationCity(@PathVariable Integer id, @ModelAttribute LocationCityChangeDTO locationCityChangeDTO) {
        locationService.updateLocationCity(id, locationCityChangeDTO);
    }

    @PutMapping("{id}/state")
    public void updateLocationState(@PathVariable Integer id, @ModelAttribute LocationStateChangeDTO locationStateChangeDTO) {
        locationService.updateLocationState(id, locationStateChangeDTO);
    }

    @PutMapping("{id}/zip")
    public void updateLocationZip(@PathVariable Integer id, @ModelAttribute LocationZipChangeDTO locationZipChangeDTO) {
        locationService.updateLocationZip(id, locationZipChangeDTO);
    }

    @PutMapping("{id}/store")
    public void updateLocationStore(@PathVariable Integer id, @ModelAttribute LocationStoreChangeDTO locationStoreChangeDTO) {
        locationService.updateLocationStore(id, locationStoreChangeDTO);
    }
}
