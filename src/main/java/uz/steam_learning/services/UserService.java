package uz.steam_learning.services;

import uz.steam_learning.models.Users;
import java.util.List;
import java.util.Optional;

public interface UserService {

    // Email orqali foydalanuvchini topish
    Optional<Users> findByEmail(String email);

    // Id orqali foydalanuvchini topish
    Optional<Users> findById(Long id);

    // Yangi foydalanuvchini saqlash
    Users save(Users user);

    // Foydalanuvchini yangilash
    Users update(Users user);

    // Foydalanuvchini o'chirish
    void deleteById(Long id);

    // Barcha foydalanuvchilar ro'yxatini olish
    List<Users> findAll();
}

