package com.hostelsphere.app.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hostelsphere.app.entity.Attendance;
import com.hostelsphere.app.entity.User;
import com.hostelsphere.app.service.AttendanceService;
import com.hostelsphere.app.service.UserService;

@Controller
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final UserService userService;

    public AttendanceController(AttendanceService attendanceService,
                                UserService userService) {
        this.attendanceService = attendanceService;
        this.userService = userService;
    }

    @GetMapping("/my-attendance")
    public String viewAttendance(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);

        List<Attendance> attendanceList =
                attendanceService.getAttendanceForUser(user);

        model.addAttribute("attendanceList", attendanceList);
        return "attendance";
    }

}
