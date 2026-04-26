package com.groceryrun.app.dto.location;

/**
 * DTO for a location
 * @param id id of the location
 * @param street street of the location
 * @param city city of the location
 * @param state state of the location
 * @param zip zip code of the location
 */
public record LocationDTO(Integer id, String street, String city, String state, String zip) {
}
