package uz.steam_learning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.steam_learning.models.Users;
import uz.steam_learning.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Users save(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users update(Users user) {
        // Agar foydalanuvchi mavjud bo'lsa, yangilaydi, aks holda saqlaydi
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Foydalanuvchi topilmadi yoki id noto'g'ri");
        }
    }

    @Override
    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Foydalanuvchi topilmadi yoki id noto'g'ri");
        }
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }
}
