package com.hostelsphere.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostelsphere.app.entity.Attendance;
import com.hostelsphere.app.entity.User;
import com.hostelsphere.app.service.AttendanceService;
import com.hostelsphere.app.service.UserService;

@Controller
public class AdminAttendanceController {

    private final AttendanceService attendanceService;
    private final UserService userService;

    public AdminAttendanceController(AttendanceService attendanceService,
                                     UserService userService) {
        this.attendanceService = attendanceService;
        this.userService = userService;
    }

    @GetMapping("/admin/attendance")
    public String attendancePage(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,
            Model model) {

        if (date == null) {
            date = LocalDate.now();
        }

        List<User> students = userService.getAllStudents();
        List<Attendance> attendanceList =
                attendanceService.getAttendanceByDate(date);

        Map<Long, String> attendanceMap = new HashMap<>();

        for (Attendance a : attendanceList) {
            attendanceMap.put(
                    a.getUser().getId(),
                    a.getStatus()
            );
        }

        model.addAttribute("date", date);
        model.addAttribute("students", students);
        model.addAttribute("attendanceMap", attendanceMap);

        return "admin-attendance";
    }

    @PostMapping("/admin/attendance/save")
    public String saveAttendance(
            @RequestParam Long userId,
            @RequestParam String status,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        User user = userService.getUserById(userId);
        attendanceService.markAttendance(user, date, status);

        return "redirect:/admin/attendance?date=" + date;
    }
}
