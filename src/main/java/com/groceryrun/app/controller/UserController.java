package com.groceryrun.app.controller;


import com.groceryrun.app.dto.user.*;
import com.groceryrun.app.dto.shared.GroceryListsChangeDTO;
import com.groceryrun.app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("id/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("username/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public void addNewUser(@ModelAttribute RegisterDTO registerDTO) {
        userService.addUser(registerDTO);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("username")
    public void updateUserName(Principal principal, @RequestBody UsernameChangeDTO usernameChangeDTO) {
        userService.updateUserName(principal.getName(), usernameChangeDTO);
    }

    @PutMapping("password")
    public void updatePassword(Principal principal, @RequestBody PasswordChangeDTO passwordChangeDTO) {
        userService.updateUserPassword(principal.getName(), passwordChangeDTO);
    }

    @PutMapping("admin/{id}/role")
    public void updateRole(Principal principal, @PathVariable Integer id, @RequestBody RoleChangeDTO roleChangeDTO) {
        userService.updateUserRole(principal.getName(), id, roleChangeDTO);
    }

    @PutMapping("{id}/grocery-lists")
    public void updateGroceryLists(@PathVariable Integer id, @RequestBody GroceryListsChangeDTO groceryListsChangeDTO) {
        userService.updateUserGroceryLists(id, groceryListsChangeDTO);
    }

    @PutMapping("{id}/stores")
    public void updateStores(@PathVariable Integer id, @RequestBody UserStoresChangeDTO userStoresChangeDTO) {
        userService.updateUserStores(id, userStoresChangeDTO);
    }
}
