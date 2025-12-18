package com.hostelsphere.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
            	    // PUBLIC
            	    .requestMatchers("/login", "/register", "/debug-auth").permitAll()

            	    // ADMIN ONLY
            	    .requestMatchers("/admin/**").hasRole("ADMIN")

            	    // STUDENT ONLY
            	    .requestMatchers("/attendance").hasRole("STUDENT")

            	    // BOTH
            	    .requestMatchers(
            	            "/complaint/**",
            	            "/meals"
            	    ).hasAnyRole("STUDENT", "ADMIN")

            	    // EVERYTHING ELSE
            	    .anyRequest().authenticated()
            	)


            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/complaint/new", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
