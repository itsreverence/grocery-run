package com.groceryrun.app.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.groceryrun.app.dto.item.ItemDTOMapper;
import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.Category;
import com.groceryrun.app.entities.Item;
import com.groceryrun.app.entities.Location;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.entities.User;
import com.groceryrun.app.repositories.CategoryRepository;
import com.groceryrun.app.repositories.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ItemDTOMapper itemDTOMapper;

    private ItemService itemService;
    private User user;
    private Store store;
    private Aisle aisle;
    private Category category;
    private Item item;

    @BeforeEach
    public void setUp() {
        itemService = new ItemService(itemRepository, itemDTOMapper, categoryRepository);
        user = new User("owner", "password");
        store = new Store("Store", null, new ArrayList<>(List.of(user)));
        store.setLocation(new Location("Street", "City", "State", "12345", store));
        aisle = new Aisle("Aisle", store);
        category = new Category("Category", aisle);
        item = new Item("Item", category);
    }

    @Test
    public void testDeleteItemFromMissingCategoryThrowsException() {
        String username = "owner";
        Integer categoryId = 1;
        Integer itemId = 1;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> itemService.deleteItem(username, itemId, categoryId));
    }

    @Test
    public void testDeleteMissingItemThrowsException() {
        String username = "owner";
        Integer categoryId = 1;
        Integer itemId = 1;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> itemService.deleteItem(username, itemId, categoryId));
    }

    @Test
    public void testDeleteItemFromUnownedStoreThrowsException() {
        String username = "otherOwner";
        Integer categoryId = 1;
        Integer itemId = 1;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        assertThrows(IllegalStateException.class, () -> itemService.deleteItem(username, itemId, categoryId));
    }

    @Test
    public void testDeleteItemNotInCategoryThrowsException() {
        String username = "owner";
        Integer categoryId = 1;
        Integer itemId = 1;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        assertThrows(IllegalStateException.class, () -> itemService.deleteItem(username, itemId, categoryId));
    }

    @Test
    public void testDeleteItemSuccessfully() {
        String username = "owner";
        Integer categoryId = 1;
        Integer itemId = 1;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        category.getItems().add(item);
        itemService.deleteItem(username, itemId, categoryId);
        assertFalse(category.getItems().contains(item));
        verify(categoryRepository).save(category);
        verify(itemRepository).delete(item);
    }
}
