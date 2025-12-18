package com.hostelsphere.app.service;

import java.util.List;

import com.hostelsphere.app.dto.RegisterDto;
import com.hostelsphere.app.entity.User;

public interface UserService {

    // existing
    User registerUser(RegisterDto registerDto);

    User getUserByEmail(String email);

    // 🔽 ADD THESE FOR ATTENDANCE
    User getUserById(Long id);

    List<User> getAllStudents();
}
