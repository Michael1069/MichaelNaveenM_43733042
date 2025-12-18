package com.hostelsphere.app.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hostelsphere.app.entity.Role;
import com.hostelsphere.app.repository.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if STUDENT role exists, if not create it
        if (roleRepository.findByName("STUDENT") == null) {
            Role studentRole = new Role();
            studentRole.setName("STUDENT");
            roleRepository.save(studentRole);
            System.out.println("✓ STUDENT role created");
        }

        // Check if ADMIN role exists, if not create it
        if (roleRepository.findByName("ADMIN") == null) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
            System.out.println("✓ ADMIN role created");
        }
    }
}