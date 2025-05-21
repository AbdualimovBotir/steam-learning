package uz.steam_learning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.steam_learning.models.Users;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    // Email orqali foydalanuvchini topish
    Optional<Users> findByEmail(String email);

    // Foydalanuvchi ismi bo‘yicha qidirish (agar kerak bo‘lsa)
    List<Users> findByFullNameContainingIgnoreCase(String fullName);

    // Holati bo‘yicha foydalanuvchilarni topish
    List<Users> findByStatus(uz.steam_learning.models.UserStatus status);

    // Ma’lum roldagi foydalanuvchilarni topish (Role orqali emas, bevosita Role.id bilan ishlash)
    List<Users> findByRole_Id(Long roleId);

    // Ma’lum rol nomi bo‘yicha foydalanuvchilarni olish (JOIN orqali query yozish kerak bo‘lsa, @Query bilan yoziladi)

    // @Query orqali qo‘shimcha metod
    @Query("SELECT u FROM Users u WHERE u.createdAt BETWEEN :start AND :end")
    List<Users> findAllByCreatedAtBetween(Timestamp start, Timestamp end);

}

