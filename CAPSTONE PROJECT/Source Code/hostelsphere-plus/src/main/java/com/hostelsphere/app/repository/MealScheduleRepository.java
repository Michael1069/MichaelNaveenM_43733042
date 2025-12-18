package com.hostelsphere.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostelsphere.app.entity.MealSchedule;

public interface MealScheduleRepository
        extends JpaRepository<MealSchedule, Long> {

    MealSchedule findByDate(LocalDate date);

    List<MealSchedule> findAllByOrderByDateAsc();
}
