package com.hostelsphere.app.service;

import java.time.LocalDate;
import java.util.List;

import com.hostelsphere.app.entity.Attendance;
import com.hostelsphere.app.entity.User;

public interface AttendanceService {

    Attendance markAttendance(User user, LocalDate date, String status);

    List<Attendance> getAttendanceByDate(LocalDate date);

    List<Attendance> getAttendanceForUser(User user);
}
