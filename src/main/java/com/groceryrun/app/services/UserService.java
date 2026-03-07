package com.groceryrun.app.services;

import com.groceryrun.app.dto.user.*;
import com.groceryrun.app.entities.User;
import com.groceryrun.app.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userDTOMapper).collect(Collectors.toList());
    }

    public void addUser(RegisterDTO registerDTO) {
        boolean exists = userRepository.existsByUsername(registerDTO.username());
        if (exists) {
            throw new IllegalStateException(registerDTO.username() + " already exists");
        }
        String passwordHash = passwordEncoder.encode(registerDTO.password());
        User user = new User(registerDTO.username(), passwordHash);
        userRepository.save(user);
    }

    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id).map(userDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public UserDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userDTOMapper).orElseThrow(() -> new IllegalStateException(username + " not found"));
    }

    public void deleteUser(Integer id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        userRepository.deleteById(id);
    }

    public void updateUserPassword(Integer id, PasswordChangeDTO passwordChangeDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        String storedOldPasswordHash = user.getPasswordHash();
        boolean matches = passwordEncoder.matches(passwordChangeDTO.currentPassword(), storedOldPasswordHash);
        if (!matches) {
            throw new IllegalStateException("The provided current password does not match");
        }
        String newPasswordHash = passwordEncoder.encode(passwordChangeDTO.newPassword());
        user.setPasswordHash(newPasswordHash);
        userRepository.save(user);
    }

    public void updateUserName(Integer id, UsernameChangeDTO usernameChangeDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        boolean exists = userRepository.existsByUsername(usernameChangeDTO.newUsername());
        if (exists) {
            throw new IllegalStateException(usernameChangeDTO.newUsername() + " already exists");
        }
        user.setUsername(usernameChangeDTO.newUsername());
        userRepository.save(user);
    }

    public void updateUserRole(Integer id, RoleChangeDTO roleChangeDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        user.setRole(roleChangeDTO.newRole());
        userRepository.save(user);
    }
}
