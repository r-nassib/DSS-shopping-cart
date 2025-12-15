package com.cart.cart.config;

import com.cart.cart.model.Role;
import com.cart.cart.model.User;
import com.cart.cart.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner seedUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(new User(
                        "admin",
                        passwordEncoder.encode("admin"),
                        Set.of(Role.ADMIN, Role.MANAGER)
                ));
            }
            if (userRepository.findByUsername("manager").isEmpty()) {
                userRepository.save(new User(
                        "manager",
                        passwordEncoder.encode("manager"),
                        Set.of(Role.MANAGER)
                ));
            }
            if (userRepository.findByUsername("cliente").isEmpty()) {
                userRepository.save(new User(
                        "cliente",
                        passwordEncoder.encode("cliente"),
                        Set.of(Role.CLIENT)
                ));
            }
        };
    }
}

