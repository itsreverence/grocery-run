package com.groceryrun.app.controller;


import com.groceryrun.app.dto.user.*;
import com.groceryrun.app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller for users
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService; // Service for user data
    
    /**
     * Constructor for user controller
     * @param userService service for user data
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves all users from the database
     * @return list of user DTOs
     */
    @GetMapping("admin")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * Retrieves a user by its id
     * @param id id of the user
     * @return user DTO
     */
    @GetMapping("admin/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    /**
     * Retrieves a user by its username
     * @param username username of the user
     * @return user DTO
     */
    @GetMapping("admin/username/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    /**
     * Adds a new user to the database
     * @param registerDTO register data
     */
    @PostMapping
    public void addNewUser(@ModelAttribute RegisterDTO registerDTO) {
        userService.addUser(registerDTO);
    }

    /**
     * Deletes a user from the database
     * @param principal principal of the user
     */
    @DeleteMapping
    public void deleteUser(Principal principal) {
        userService.deleteUser(principal.getName());
    }

    /**
     * Deletes a user from the database by its id
     * @param id id of the user
     */
    @DeleteMapping("admin/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    /**
     * Updates the username of a user
     * @param principal principal of the user
     * @param usernameChangeDTO new username
     */
    @PutMapping("username")
    public void updateUserName(Principal principal, @RequestBody UsernameChangeDTO usernameChangeDTO) {
        userService.updateUserName(principal.getName(), usernameChangeDTO);
    }

    /**
     * Updates the password of a user
     * @param principal principal of the user
     * @param passwordChangeDTO new password
     */
    @PutMapping("password")
    public void updatePassword(Principal principal, @RequestBody PasswordChangeDTO passwordChangeDTO) {
        userService.updateUserPassword(principal.getName(), passwordChangeDTO);
    }

    /**
     * Updates the role of a user
     * @param principal principal of the user
     * @param id id of the user
     * @param roleChangeDTO new role
     */
    @PutMapping("admin/{id}/role")
    public void updateRole(Principal principal, @PathVariable Integer id, @RequestBody RoleChangeDTO roleChangeDTO) {
        userService.updateUserRole(principal.getName(), id, roleChangeDTO);
    }
}
