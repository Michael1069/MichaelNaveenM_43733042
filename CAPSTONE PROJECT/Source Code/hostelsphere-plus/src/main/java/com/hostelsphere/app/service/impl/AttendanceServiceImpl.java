package com.hostelsphere.app.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelsphere.app.entity.Attendance;
import com.hostelsphere.app.entity.User;
import com.hostelsphere.app.repository.AttendanceRepository;
import com.hostelsphere.app.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Attendance markAttendance(User user, LocalDate date, String status) {

        Attendance attendance =
                attendanceRepository.findByUserAndDate(user, date);

        if (attendance == null) {
            attendance = new Attendance();
            attendance.setUser(user);
            attendance.setDate(date);
        }

        attendance.setStatus(status);
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findAllByDate(date);
    }

    @Override
    public List<Attendance> getAttendanceForUser(User user) {
        return attendanceRepository.findAllByUserOrderByDateDesc(user);
    }
}
