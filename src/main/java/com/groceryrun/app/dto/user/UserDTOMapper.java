package com.groceryrun.app.dto.user;

import com.groceryrun.app.entities.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper for user data
 */
@Service
public class UserDTOMapper implements Function<User, UserDTO> {    
    /**
     * Maps a user entity to a user DTO
     * @param user user entity
     * @return user DTO
     */
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(user.getId(), user.getRole(), user.getUsername());
    }
}
