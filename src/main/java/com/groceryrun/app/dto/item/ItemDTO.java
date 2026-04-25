package com.groceryrun.app.dto.item;

/**
 * DTO for an item in the grocery store
 * @param id id of the item
 * @param name name of the item
 */
public record ItemDTO(Integer id, String name) {
}
