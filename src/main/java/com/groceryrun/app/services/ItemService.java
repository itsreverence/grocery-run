package com.groceryrun.app.services;

import com.groceryrun.app.dto.item.ItemDTO;
import com.groceryrun.app.dto.item.ItemDTOMapper;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.dto.item.NewItemDTO;
import com.groceryrun.app.entities.Category;
import com.groceryrun.app.entities.Item;
import com.groceryrun.app.repositories.ItemRepository;
import com.groceryrun.app.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Item service which contains the business logic for retrieving, adding, deleting, and updating items
 */
@Service
public class ItemService {
    private final ItemRepository itemRepository; // Repository for item data
    private final CategoryRepository categoryRepository; // Repository for category data
    private final ItemDTOMapper itemDTOMapper; // Mapper for item data

    /**
     * Constructor for item service
     * @param itemRepository repository for item data
     * @param itemDTOMapper mapper for item data
     * @param categoryRepository repository for category data
     */
    public ItemService(ItemRepository itemRepository, ItemDTOMapper itemDTOMapper, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.itemDTOMapper = itemDTOMapper;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves all items from the database
     * @return list of item DTOs
     */
    public List<ItemDTO> getAllItems() {
        Map<String, Item> itemsByName = new LinkedHashMap<>();
        itemRepository.findAll().stream()
                .filter(item -> item.getName() != null)
                .sorted(Comparator.comparing(Item::getId))
                .forEach(item -> itemsByName.putIfAbsent(item.getName().trim().toLowerCase(), item));
        return itemsByName.values().stream().map(itemDTOMapper).collect(Collectors.toList());
    }

    /**
     * Adds an item to a category
     * @param username username of the user
     * @param categoryId id of the category
     * @param newItemDTO new item data
     * @throws IllegalStateException if the category is not found or the user is not an owner of the store containing the category
     */
    public void addItem(String username, Integer categoryId, NewItemDTO newItemDTO) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException(categoryId + " not found"));
        if (!category.getAisle().getStore().getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of category " + categoryId);
        }
        Item item = new Item(newItemDTO.name(), category);
        itemRepository.save(item);
        category.getItems().add(item);
        categoryRepository.save(category);
    }

    /**
     * Retrieves an item by its id
     * @param id id of the item
     * @return item DTO
     * @throws IllegalStateException if the item is not found
     */
    public ItemDTO getItemById(Integer id) {
        return itemRepository.findById(id).map(itemDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    /**
     * Deletes an item from a category
     * @param username username of the user
     * @param id id of the item
     * @param categoryId id of the category
     * @throws IllegalStateException if the category is not found or the user is not an owner of the store containing the category
     */
    public void deleteItem(String username, Integer id, Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException(categoryId + " not found"));
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!category.getAisle().getStore().getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of category " + categoryId);
        }
        if (!category.getItems().contains(item)) {
            throw new IllegalStateException("Item " + id + " is not in category " + categoryId);
        }
        category.getItems().remove(item);
        categoryRepository.save(category);
        itemRepository.delete(item);
    }

    /**
     * Updates the name of an item
     * @param username username of the user
     * @param id id of the item
     * @param nameChangeDTO new name
     * @throws IllegalStateException if the item is not found or the user is not an owner of the store containing the item
     */
    public void updateItemName(String username, Integer id, NameChangeDTO nameChangeDTO) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!item.getCategory().getAisle().getStore().getOwners().stream().anyMatch(owner -> owner.getUsername().equals(username))) {
            throw new IllegalStateException("User " + username + " is not an owner of item " + id);
        }
        item.setName(nameChangeDTO.newName());
        itemRepository.save(item);
    }
}
