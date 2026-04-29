package com.groceryrun.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.groceryrun.app.dto.grocerylist.GroceryListDTOMapper;
import com.groceryrun.app.dto.grocerylist.NewGroceryListDTO;
import com.groceryrun.app.entities.GroceryList;
import com.groceryrun.app.entities.Item;
import com.groceryrun.app.entities.User;
import com.groceryrun.app.repositories.GroceryListRepository;
import com.groceryrun.app.repositories.ItemRepository;
import com.groceryrun.app.repositories.UserRepository;

/**
 * Unit tests for the create grocery list use case
 */
@ExtendWith(MockitoExtension.class)
public class GroceryListServiceTest {

    @Mock
    private GroceryListRepository groceryListRepository; // Repository for grocery list data

    @Mock
    private UserRepository userRepository; // Repository for user data

    @Mock
    private ItemRepository itemRepository; // Repository for item data

    @Mock
    private GroceryListDTOMapper groceryListDTOMapper; // Mapper for grocery list data

    private GroceryListService groceryListService;
    private User user;
    private User otherUser;
    private GroceryList groceryList;
    private Item item;

    @BeforeEach
    public void setUp() {
        groceryListService = new GroceryListService(
                groceryListRepository,
                userRepository,
                itemRepository,
                groceryListDTOMapper);
        user = new User("owner", "password");
        otherUser = new User("otherOwner", "password");
        groceryList = new GroceryList("My List", user);
        item = new Item("Milk", null);
    }

    /**
     * Adds a grocery list when the user is not found
     */
    @Test
    public void testAddGroceryListThrowsExceptionWhenUserNotFound() {
        String username = "missingUser";
        NewGroceryListDTO dto = new NewGroceryListDTO("Weekly shop");
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> groceryListService.addGroceryList(username, dto));
    }

    /**
     * Adds a grocery list successfully
     */
    @Test
    public void testAddGroceryListSuccessfully() {
        String username = "owner";
        NewGroceryListDTO dto = new NewGroceryListDTO("Weekly shop");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        groceryListService.addGroceryList(username, dto);

        ArgumentCaptor<GroceryList> listCaptor = ArgumentCaptor.forClass(GroceryList.class);
        verify(groceryListRepository).save(listCaptor.capture());
        GroceryList saved = listCaptor.getValue();
        assertEquals("Weekly shop", saved.getName());
        assertEquals(user, saved.getOwner());
        assertTrue(user.getGroceryLists().contains(saved));
        verify(userRepository).save(user);
    }

    /**
     * Unit tests for the add grocery item use case
     */
    @Nested
    class AddItemToGroceryList {

        private static final int LIST_ID = 1;
        private static final int ITEM_ID = 1;

        /**
         * Adds an item when the user is not found
         */
        @Test
        public void testThrowsExceptionWhenUserNotFound() {
            when(userRepository.findByUsername("missingUser")).thenReturn(Optional.empty());

            assertThrows(
                    IllegalStateException.class,
                    () -> groceryListService.addItemToGroceryList("missingUser", LIST_ID, ITEM_ID));
        }

        /**
         * Adds an item when the grocery list is not found
         */
        @Test
        public void testThrowsExceptionWhenGroceryListNotFound() {
            when(userRepository.findByUsername("owner")).thenReturn(Optional.of(user));
            when(groceryListRepository.findById(LIST_ID)).thenReturn(Optional.empty());

            assertThrows(
                    IllegalStateException.class,
                    () -> groceryListService.addItemToGroceryList("owner", LIST_ID, ITEM_ID));
        }

        /**
         * Adds an item when the user is not the list owner
         */
        @Test
        public void testThrowsExceptionWhenUserIsNotOwner() {
            when(userRepository.findByUsername("otherOwner")).thenReturn(Optional.of(otherUser));
            when(groceryListRepository.findById(LIST_ID)).thenReturn(Optional.of(groceryList));

            assertThrows(
                    IllegalStateException.class,
                    () -> groceryListService.addItemToGroceryList("otherOwner", LIST_ID, ITEM_ID));
        }

        /**
         * Adds an item when the item is not found
         */
        @Test
        public void testThrowsExceptionWhenItemNotFound() {
            when(userRepository.findByUsername("owner")).thenReturn(Optional.of(user));
            when(groceryListRepository.findById(LIST_ID)).thenReturn(Optional.of(groceryList));
            when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.empty());

            assertThrows(
                    IllegalStateException.class,
                    () -> groceryListService.addItemToGroceryList("owner", LIST_ID, ITEM_ID));
        }

        /**
         * Adds an item successfully
         */
        @Test
        public void testAddsItemSuccessfully() {
            when(userRepository.findByUsername("owner")).thenReturn(Optional.of(user));
            when(groceryListRepository.findById(LIST_ID)).thenReturn(Optional.of(groceryList));
            when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.of(item));

            groceryListService.addItemToGroceryList("owner", LIST_ID, ITEM_ID);

            assertTrue(groceryList.getItems().contains(item));
            verify(groceryListRepository).save(groceryList);
        }
    }

    /**
     * Unit tests for the remove grocery item use case
     */
    @Nested
    class RemoveItemFromGroceryList {

        private static final int LIST_ID = 1;
        private static final int ITEM_ID = 1;

        /**
         * Removes an item when the user is not found
         */
        @Test
        public void testThrowsExceptionWhenUserNotFound() {
            when(userRepository.findByUsername("missingUser")).thenReturn(Optional.empty());

            assertThrows(
                    IllegalStateException.class,
                    () -> groceryListService.removeItemFromGroceryList("missingUser", LIST_ID, ITEM_ID));
        }

        /**
         * Removes an item when the grocery list is not found
         */
        @Test
        public void testThrowsExceptionWhenGroceryListNotFound() {
            when(userRepository.findByUsername("owner")).thenReturn(Optional.of(user));
            when(groceryListRepository.findById(LIST_ID)).thenReturn(Optional.empty());

            assertThrows(
                    IllegalStateException.class,
                    () -> groceryListService.removeItemFromGroceryList("owner", LIST_ID, ITEM_ID));
        }

        /**
         * Removes an item when the user is not the list owner
         */
        @Test
        public void testThrowsExceptionWhenUserIsNotOwner() {
            when(userRepository.findByUsername("otherOwner")).thenReturn(Optional.of(otherUser));
            when(groceryListRepository.findById(LIST_ID)).thenReturn(Optional.of(groceryList));

            assertThrows(
                    IllegalStateException.class,
                    () -> groceryListService.removeItemFromGroceryList("otherOwner", LIST_ID, ITEM_ID));
        }

        /**
         * Removes an item when the item is not found
         */
        @Test
        public void testThrowsExceptionWhenItemNotFound() {
            when(userRepository.findByUsername("owner")).thenReturn(Optional.of(user));
            when(groceryListRepository.findById(LIST_ID)).thenReturn(Optional.of(groceryList));
            when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.empty());

            assertThrows(
                    IllegalStateException.class,
                    () -> groceryListService.removeItemFromGroceryList("owner", LIST_ID, ITEM_ID));
        }

        /**
         * Removes an item successfully
         */
        @Test
        public void testRemovesItemSuccessfully() {
            groceryList.getItems().add(item);
            when(userRepository.findByUsername("owner")).thenReturn(Optional.of(user));
            when(groceryListRepository.findById(LIST_ID)).thenReturn(Optional.of(groceryList));
            when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.of(item));

            groceryListService.removeItemFromGroceryList("owner", LIST_ID, ITEM_ID);

            assertFalse(groceryList.getItems().contains(item));
            verify(groceryListRepository).save(groceryList);
        }
    }
}
