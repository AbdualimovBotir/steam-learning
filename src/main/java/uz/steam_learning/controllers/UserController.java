package uz.steam_learning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.steam_learning.dtos.UserDTO;
import uz.steam_learning.mappers.UserMapper;
import uz.steam_learning.models.Role;
import uz.steam_learning.models.UserRole;
import uz.steam_learning.models.Users;
import uz.steam_learning.repositories.RoleRepository;
import uz.steam_learning.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Ro'yxatdan o'tish - yangi foydalanuvchi yaratish
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ushbu email bilan foydalanuvchi mavjud!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByRoleName(UserRole.ROLE_CUSTOMER)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRoleName(UserRole.ROLE_CUSTOMER);
                    newRole.setDescription("Default customer role");
                    return roleRepository.save(newRole);
                });

        user.setRole(role);
        userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Foydalanuvchi muvaffaqiyatli ro'yxatdan o'tdi");
    }

    // Hozirgi login qilingan foydalanuvchini olish
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        String email = principal.getName();

        Users user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi"));

        UserDTO userDTO = UserMapper.toDto(user);
        return ResponseEntity.ok(userDTO);
    }

    // Barcha foydalanuvchilar ro'yxatini olish
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    // Id orqali foydalanuvchini olish
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Users user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi"));

        UserDTO userDTO = UserMapper.toDto(user);
        return ResponseEntity.ok(userDTO);
    }

    // Foydalanuvchini yangilash
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        Users existingUser = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi"));

        existingUser.setFullName(userDetails.getFullName());
        existingUser.setEmail(userDetails.getEmail());

        // Agar parol berilgan bo'lsa, uni kodlash
        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        // Rolni yangilash kerak bo'lsa (masalan, admin tomonidan)
        if (userDetails.getRole() != null) {
            existingUser.setRole(userDetails.getRole());
        }

        Users updatedUser = userService.update(existingUser);

        UserDTO updatedUserDTO = UserMapper.toDto(updatedUser);
        return ResponseEntity.ok(updatedUserDTO);
    }

    // Foydalanuvchini o'chirish
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (userService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Foydalanuvchi topilmadi");
        }

        userService.deleteById(id);
        return ResponseEntity.ok("Foydalanuvchi muvaffaqiyatli o'chirildi");
    }
}
