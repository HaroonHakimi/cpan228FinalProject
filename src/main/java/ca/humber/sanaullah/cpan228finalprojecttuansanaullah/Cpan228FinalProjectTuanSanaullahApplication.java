package ca.humber.sanaullah.cpan228finalprojecttuansanaullah;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.User;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Cpan228FinalProjectTuanSanaullahApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cpan228FinalProjectTuanSanaullahApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if admin user exists, if not create it
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("password"));
                admin.setRole("ROLE_ADMIN");
                admin.setEnabled(true);
                userRepository.save(admin);
                System.out.println("Admin user created: username=admin, password=password");
            }

            // Check if regular user exists, if not create it
            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRole("ROLE_USER");
                user.setEnabled(true);
                userRepository.save(user);
                System.out.println("Regular user created: username=user, password=password");
            }

            System.out.println("User initialization complete!");
        };
    }
}