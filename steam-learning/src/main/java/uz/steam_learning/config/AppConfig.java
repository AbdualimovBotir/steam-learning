package uz.steam_learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import uz.steam_learning.repositories.RoleRepository;
import uz.steam_learning.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AppConfig(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(configurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> auth
                        // Ochiq endpointlar
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/auth/**", "/oauth2/**").permitAll()
                        // Faqat ma'lum rollarga ruxsat
                        .requestMatchers("/api/admin/**").hasAnyRole("STEAM_OWNER", "ADMIN", "CUSTOMER")
                        // Boshqa api endpointlar uchun autentifikatsiya talab qilinadi
                        .requestMatchers("/api/**").authenticated()
                        // Boshqalar uchun ruxsat
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth -> oauth
                        .defaultSuccessUrl("/api/v1/auth/oauth2/success", true)
                        .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService()))
                )
                // JwtTokenValidator ni OAuth2 login oldidan yoki keyin qo'shishiga qarab o'zgartiring
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));
            config.setExposedHeaders(List.of("Authorization"));
            config.setAllowCredentials(true);
            return config;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomOAuth2UserService oAuth2UserService() {
        return new CustomOAuth2UserService(userRepository, roleRepository);
    }
}
