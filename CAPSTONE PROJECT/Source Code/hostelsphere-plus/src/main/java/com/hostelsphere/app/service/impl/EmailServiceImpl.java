package com.hostelsphere.app.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hostelsphere.app.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendPasswordResetEmail(String toEmail, String resetLink) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Reset your HostelSphere password");
        message.setText(
                "Hello,\n\n"
              + "Click the link below to reset your password:\n"
              + resetLink + "\n\n"
              + "This link is valid for 30 minutes.\n\n"
              + "If you didn’t request this, ignore this email.\n\n"
              + "— HostelSphere Team"
        );

        mailSender.send(message);
    }
}
