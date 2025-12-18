package com.hostelsphere.app.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostelsphere.app.entity.PasswordResetToken;
import com.hostelsphere.app.entity.User;
import com.hostelsphere.app.repository.PasswordResetTokenRepository;
import com.hostelsphere.app.repository.UserRepository;
import com.hostelsphere.app.service.EmailService;

@Controller
public class PasswordController {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public PasswordController(UserRepository userRepository,
                              PasswordResetTokenRepository tokenRepository,
                              PasswordEncoder passwordEncoder,
                              EmailService emailService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    /* ================= FORGOT PASSWORD ================= */

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    @Transactional
    public String processForgotPassword(@RequestParam String email,
                                        Model model) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            model.addAttribute("error", "No account found with that email");
            return "forgot-password";
        }

        // Reuse existing token if present (ONE token per user)
        PasswordResetToken resetToken = tokenRepository.findByUser(user);

        if (resetToken == null) {
            resetToken = new PasswordResetToken();
            resetToken.setUser(user);
        }

        resetToken.setToken(UUID.randomUUID().toString());
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));

        tokenRepository.save(resetToken);

        String resetLink =
                "http://localhost:8080/reset-password?token=" + resetToken.getToken();

        emailService.sendPasswordResetEmail(user.getEmail(), resetLink);

        model.addAttribute("message", "Password reset link sent to your email");
        return "forgot-password";
    }

    /* ================= RESET PASSWORD ================= */

    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam(required = false) String token,
                                    Model model) {

        if (token == null) {
            return "redirect:/login?invalidResetLink";
        }

        PasswordResetToken resetToken = tokenRepository.findByToken(token);

        if (resetToken == null ||
            resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return "redirect:/login?invalidResetLink";
        }

        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    @Transactional
    public String processResetPassword(@RequestParam String token,
                                       @RequestParam String password,
                                       @RequestParam String confirmPassword,
                                       Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            model.addAttribute("token", token);
            return "reset-password";
        }

        PasswordResetToken resetToken = tokenRepository.findByToken(token);

        if (resetToken == null ||
            resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return "redirect:/login?invalidResetLink";
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        // Single-use token
        tokenRepository.delete(resetToken);

        return "redirect:/login?resetSuccess";
    }	
    

}
