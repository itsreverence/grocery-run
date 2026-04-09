package com.groceryrun.app.services;

import com.groceryrun.app.dto.item.ItemCategoryChangeDTO;
import com.groceryrun.app.dto.item.ItemDTO;
import com.groceryrun.app.dto.item.ItemDTOMapper;
import com.groceryrun.app.dto.shared.GroceryListsChangeDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.dto.item.NewItemDTO;
import com.groceryrun.app.entities.Category;
import com.groceryrun.app.entities.Item;
import com.groceryrun.app.repositories.ItemRepository;
import com.groceryrun.app.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemDTOMapper itemDTOMapper;

    public ItemService(ItemRepository itemRepository, ItemDTOMapper itemDTOMapper, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.itemDTOMapper = itemDTOMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream().map(itemDTOMapper).collect(Collectors.toList());
    }

    public void addItem(Integer categoryId, NewItemDTO newItemDTO) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException(categoryId + " not found"));
        Item item = new Item(newItemDTO.name(), category);
        itemRepository.save(item);
        category.getItems().add(item);
        categoryRepository.save(category);
    }

    public ItemDTO getItemById(Integer id) {
        return itemRepository.findById(id).map(itemDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void deleteItem(Integer id, Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException(categoryId + " not found"));
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (category.getItems().contains(item)) {
            category.getItems().remove(item);
            categoryRepository.save(category);
            itemRepository.delete(item);
        }
    }

    public void updateItemName(Integer id, NameChangeDTO nameChangeDTO) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        item.setName(nameChangeDTO.newName());
        itemRepository.save(item);
    }

    public void updateItemGroceryLists(Integer id, GroceryListsChangeDTO groceryListsChangeDTO) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        item.setGroceryLists(groceryListsChangeDTO.newGroceryLists());
        itemRepository.save(item);
    }

    public void updateItemCategory(Integer id, ItemCategoryChangeDTO itemCategoryChangeDTO) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        item.setCategory(itemCategoryChangeDTO.newItemCategory());
        itemRepository.save(item);
    }
}
