package com.groceryrun.app.controller;

import com.groceryrun.app.dto.location.*;
import com.groceryrun.app.services.LocationService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller for locations
 */
@RestController
@RequestMapping("api/v1/locations")
public class LocationController {
    private final LocationService locationService; // Service for location data
    
    /**
     * Constructor for location controller
     * @param locationService service for location data
     */
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Retrieves all locations from the database
     * @return list of location DTOs
     */
    @GetMapping
    public List<LocationDTO> getAllLocations() {
        return locationService.getAllLocations();
    }

    /**
     * Retrieves a location by its id
     * @param id id of the location
     * @return location DTO
     */
    @GetMapping("{id}")
    public LocationDTO getLocation(@PathVariable Integer id) {
        return locationService.getLocationById(id);
    }

    /**
     * Adds a location to a store
     * @param principal principal of the user
     * @param newLocationDTO new location data
     */
    @PostMapping("admin")
    public void addNewLocation(Principal principal, @ModelAttribute NewLocationDTO newLocationDTO) {
        locationService.addLocation(principal.getName(), newLocationDTO);
    }

    /**
     * Deletes a location from a store
     * @param principal principal of the user
     * @param id id of the location
     */
    @DeleteMapping("admin/{id}")
    public void deleteLocation(Principal principal, @PathVariable Integer id) {
        locationService.deleteLocation(principal.getName(), id);
    }

    /**
     * Updates the street of a location
     * @param principal principal of the user
     * @param id id of the location
     * @param locationStreetChangeDTO new street
     */
    @PutMapping("admin/{id}/street")
    public void updateLocationStreet(Principal principal, @PathVariable Integer id, @ModelAttribute LocationStreetChangeDTO locationStreetChangeDTO) {
        locationService.updateLocationStreet(principal.getName(), id, locationStreetChangeDTO);
    }

    /**
     * Updates the city of a location
     * @param principal principal of the user
     * @param id id of the location
     * @param locationCityChangeDTO new city
     */
    @PutMapping("admin/{id}/city")
    public void updateLocationCity(Principal principal, @PathVariable Integer id, @ModelAttribute LocationCityChangeDTO locationCityChangeDTO) {
        locationService.updateLocationCity(principal.getName(), id, locationCityChangeDTO);
    }

    /**
     * Updates the state of a location
     * @param principal principal of the user
     * @param id id of the location
     * @param locationStateChangeDTO new state
     */
    @PutMapping("admin/{id}/state")
    public void updateLocationState(Principal principal, @PathVariable Integer id, @ModelAttribute LocationStateChangeDTO locationStateChangeDTO) {
        locationService.updateLocationState(principal.getName(), id, locationStateChangeDTO);
    }

    /**
     * Updates the zip code of a location
     * @param principal principal of the user
     * @param id id of the location
     * @param locationZipChangeDTO new zip code
     */
    @PutMapping("admin/{id}/zip")
    public void updateLocationZip(Principal principal, @PathVariable Integer id, @ModelAttribute LocationZipChangeDTO locationZipChangeDTO) {
        locationService.updateLocationZip(principal.getName(), id, locationZipChangeDTO);
    }

    /**
     * Updates the store of a location
     * @param principal principal of the user
     * @param id id of the location
     * @param locationStoreChangeDTO new store
     */
    @PutMapping("admin/{id}/store")
    public void updateLocationStore(Principal principal, @PathVariable Integer id, @ModelAttribute LocationStoreChangeDTO locationStoreChangeDTO) {
        locationService.updateLocationStore(principal.getName(), id, locationStoreChangeDTO);
    }
}
