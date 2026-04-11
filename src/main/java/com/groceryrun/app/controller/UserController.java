package com.groceryrun.app.controller;


import com.groceryrun.app.dto.user.*;
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

    @GetMapping("admin")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("admin/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("admin/username/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public void addNewUser(@ModelAttribute RegisterDTO registerDTO) {
        userService.addUser(registerDTO);
    }

    @DeleteMapping
    public void deleteUser(Principal principal) {
        userService.deleteUser(principal.getName());
    }

    @DeleteMapping("admin/{id}")
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
}
