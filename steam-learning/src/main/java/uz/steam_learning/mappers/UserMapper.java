package uz.steam_learning.mappers;

import uz.steam_learning.dtos.UserDTO;
import uz.steam_learning.models.Users;

public class UserMapper {

    public static UserDTO toDto(Users user) {
        if (user == null) {
            return null;
        }

        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().getRoleName(), // UserRole enum
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

}
