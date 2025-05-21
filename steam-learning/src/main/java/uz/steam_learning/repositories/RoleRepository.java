package uz.steam_learning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.steam_learning.models.Role;
import uz.steam_learning.models.UserRole;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Enum orqali ro'lni topish
    Optional<Role> findByRoleName(UserRole roleName);
}
