package uz.steam_learning.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import uz.steam_learning.models.Role;
import uz.steam_learning.models.UserRole;
import uz.steam_learning.models.UserStatus;
import uz.steam_learning.models.Users;
import uz.steam_learning.repositories.RoleRepository;
import uz.steam_learning.repositories.UserRepository;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CustomOAuth2UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // Emailni olishda null bo'lishi mumkinligini hisobga olish
        String email = (String) attributes.get("email");
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email not found from OAuth2 provider");
        }

        Optional<Users> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            Users user = new Users();
            user.setEmail(email);

            // "name" maydoni bo'lmasligi mumkin, shuning uchun tekshirish
            String fullName = (String) attributes.get("name");
            if (fullName == null) {
                fullName = email;  // Fallback qilib email ni qo'yish mumkin
            }
            user.setFullName(fullName);

            user.setPassword(""); // OAuth2 uchun parol kerak emas
            user.setStatus(UserStatus.ACTIVE);

            // Role ni bazadan olish yoki yangi yaratish
            Role role = roleRepository.findByRoleName(UserRole.ROLE_CUSTOMER)
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setRoleName(UserRole.ROLE_CUSTOMER);
                        return roleRepository.save(newRole);
                    });

            user.setRole(role);

            userRepository.save(user);
        }

        // Foydalanuvchi roli asosida SimpleGrantedAuthority berish kerak
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER")),
                attributes,
                "email"
        );
    }
}
