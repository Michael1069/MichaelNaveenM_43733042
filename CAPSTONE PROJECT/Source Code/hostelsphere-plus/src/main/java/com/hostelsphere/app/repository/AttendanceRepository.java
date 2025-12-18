package com.hostelsphere.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostelsphere.app.entity.Attendance;
import com.hostelsphere.app.entity.User;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    Attendance findByUserAndDate(User user, LocalDate date);

    List<Attendance> findAllByDate(LocalDate date);

    List<Attendance> findAllByUserOrderByDateDesc(User user);
}
