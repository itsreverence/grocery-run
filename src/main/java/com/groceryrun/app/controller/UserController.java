package com.groceryrun.app.controller;


import com.groceryrun.app.dto.user.*;
import com.groceryrun.app.services.UserService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("{id}/username")
    public void updateUserName(@PathVariable Integer id, @RequestBody UsernameChangeDTO usernameChangeDTO) {
        userService.updateUserName(id, usernameChangeDTO);
    }

    @PutMapping("{id}/password")
    public void updatePassword(@PathVariable Integer id, @RequestBody PasswordChangeDTO passwordChangeDTO) {
        userService.updateUserPassword(id, passwordChangeDTO);
    }

    @PutMapping("{id}/role")
    public void updateRole(@PathVariable Integer id, @RequestBody RoleChangeDTO roleChangeDTO) {
        userService.updateUserRole(id, roleChangeDTO);
    }

    @PutMapping("{id}/grocery-lists")
    public void updateGroceryLists(@PathVariable Integer id, @RequestBody GroceryListsChangeDTO groceryListsChangeDTO) {
        userService.updateUserGroceryLists(id, groceryListsChangeDTO);
    }
}
