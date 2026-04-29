package com.groceryrun.app.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.groceryrun.app.dto.user.UserDTOMapper;
import com.groceryrun.app.entities.GroceryList;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.entities.User;
import com.groceryrun.app.repositories.GroceryListRepository;
import com.groceryrun.app.repositories.StoreRepository;
import com.groceryrun.app.repositories.UserRepository;

/**
 * Unit tests for the delete account use case.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDTOMapper userDTOMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private GroceryListRepository groceryListRepository;

    @Mock
    private StoreRepository storeRepository;

    private UserService userService;
    private User user;

    @BeforeEach
    public void setUp() {
        userService = new UserService(
                userRepository,
                userDTOMapper,
                passwordEncoder,
                groceryListRepository,
                storeRepository);
        user = new User("alice", "hash");
    }

    /**
     * Delete account — failure when no user exists for the given username.
     */
    @Test
    public void testDeleteUserThrowsExceptionWhenUserNotFound() {
        String username = "missingUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> userService.deleteUser(username));
        verify(userRepository, never()).delete(any());
    }

    /**
     * Delete account — success when the user has no grocery lists or stores.
     */
    @Test
    public void testDeleteUserSuccessfullyWithoutAssociations() {
        String username = "alice";
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        userService.deleteUser(username);

        verify(groceryListRepository, never()).delete(any());
        verify(storeRepository, never()).save(any());
        verify(userRepository).delete(user);
    }

    /**
     * Delete account — success when grocery lists are deleted and the user is removed from owned stores.
     */
    @Test
    public void testDeleteUserDeletesGroceryListsAndRemovesStoreOwnership() {
        String username = "alice";
        GroceryList firstList = new GroceryList("Weekly shop", user);
        GroceryList secondList = new GroceryList("Party shop", user);
        Store ownedStore = new Store("Downtown", null, new ArrayList<>());
        ownedStore.getOwners().add(user);

        user.getGroceryLists().add(firstList);
        user.getGroceryLists().add(secondList);
        user.getStores().add(ownedStore);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        userService.deleteUser(username);

        verify(groceryListRepository).delete(firstList);
        verify(groceryListRepository).delete(secondList);
        assertFalse(ownedStore.getOwners().contains(user));
        verify(storeRepository).save(ownedStore);
        verify(userRepository).delete(user);
    }

    /**
     * Delete account — skips saving a store when the store list contains an entry that does not own the user.
     */
    @Test
    public void testDeleteUserSkipsStoreSaveWhenStoreDoesNotContainUser() {
        String username = "alice";
        User otherOwner = new User("bob", "hash");
        Store unrelatedStore = new Store("Uptown", null, new ArrayList<>());
        unrelatedStore.getOwners().add(otherOwner);
        user.getStores().add(unrelatedStore);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        userService.deleteUser(username);

        verify(storeRepository, never()).save(any());
        verify(userRepository).delete(user);
    }
}
