package com.hostelsphere.app.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hostelsphere.app.dto.RegisterDto;
import com.hostelsphere.app.entity.Role;
import com.hostelsphere.app.entity.User;
import com.hostelsphere.app.repository.RoleRepository;
import com.hostelsphere.app.repository.UserRepository;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /* ---------- LOGIN ---------- */

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /* ---------- REGISTER ---------- */

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(
            @ModelAttribute("registerDto") RegisterDto dto,
            Model model) {

        try {
            // Prevent duplicate email
            if (userRepository.findByEmail(dto.getEmail()) != null) {
                model.addAttribute("error", "Email already registered");
                return "register";
            }

            // Get or create STUDENT role
            Role studentRole = roleRepository.findByName("STUDENT");
            if (studentRole == null) {
                // Create role if it doesn't exist
                studentRole = new Role();
                studentRole.setName("STUDENT");
                studentRole = roleRepository.save(studentRole);
            }

            // Create user
            User user = new User();
            user.setFullName(dto.getFullName());
            user.setEmail(dto.getEmail());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setRole(studentRole);

            userRepository.save(user);

            return "redirect:/login?registered";
            
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }
}