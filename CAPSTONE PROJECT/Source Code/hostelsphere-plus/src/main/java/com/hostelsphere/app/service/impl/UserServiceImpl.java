package com.hostelsphere.app.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hostelsphere.app.dto.RegisterDto;
import com.hostelsphere.app.entity.Role;
import com.hostelsphere.app.entity.User;
import com.hostelsphere.app.repository.RoleRepository;
import com.hostelsphere.app.repository.UserRepository;
import com.hostelsphere.app.service.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(RegisterDto registerDto) {

        // Check if email already exists
        if (userRepository.findByEmail(registerDto.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        // Get or create default STUDENT role
        Role studentRole = roleRepository.findByName("STUDENT");
        if (studentRole == null) {
            studentRole = new Role();
            studentRole.setName("STUDENT");
            studentRole = roleRepository.save(studentRole);
        }

        // Create new user
        User user = new User();
        user.setFullName(registerDto.getFullName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(studentRole);

        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllStudents() {
        return userRepository.findByRole_Name("STUDENT");
    }

}
