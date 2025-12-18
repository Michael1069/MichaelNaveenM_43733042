package com.hostelsphere.app.service;

import java.time.LocalDate;
import java.util.List;

import com.hostelsphere.app.entity.MealSchedule;

public interface MealScheduleService {

    MealSchedule saveMeal(MealSchedule meal);

    MealSchedule getByDate(LocalDate date);

    List<MealSchedule> getAllMeals();

    void deleteById(Long id);
}