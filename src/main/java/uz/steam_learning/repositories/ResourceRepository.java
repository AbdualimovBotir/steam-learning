package uz.steam_learning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.steam_learning.models.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    // findAll(), findById(), save(), deleteById(), existsById() — barchasi avtomatik mavjud
}
