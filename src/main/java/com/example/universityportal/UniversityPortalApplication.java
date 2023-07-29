package com.example.universityportal;

import com.example.universityportal.auth.AuthenticationService;
import com.example.universityportal.auth.RegisterRequest;
import com.example.universityportal.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.universityportal.entity.Role.ADMIN;
import static com.example.universityportal.entity.Role.MANAGER;

@SpringBootApplication
@RequiredArgsConstructor

public class UniversityPortalApplication {
    @Value("${admin.init.password}")
    private String adminPassword;

    @Value("${admin.init.password}")
    private String managerPassword;

    public static void main(String[] args) {
        SpringApplication.run(UniversityPortalApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService authService
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@gmail.com")
                    .password(adminPassword)
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + authService.register(admin).getToken());

            var manager = RegisterRequest.builder()
                    .firstname("Manager")
                    .lastname("Manager")
                    .email("manager@gmail.com")
                    .password(managerPassword)
                    .role(MANAGER)
                    .build();
            System.out.println("Manager token: " + authService.register(manager).getToken());
        };
    }

}
