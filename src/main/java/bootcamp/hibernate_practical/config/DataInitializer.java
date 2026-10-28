package bootcamp.hibernate_practical.config;

import bootcamp.hibernate_practical.entity.User;
import bootcamp.hibernate_practical.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedUsers(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.findByUsername("user").isEmpty()) {
                userRepository.save(new User("user", encoder.encode("user"), "USER"));
            }
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(new User("admin", encoder.encode("admin"), "ADMIN"));
            }
        };
    }
}
