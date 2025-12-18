package com.hostelsphere.app.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelsphere.app.entity.MealSchedule;
import com.hostelsphere.app.repository.MealScheduleRepository;
import com.hostelsphere.app.service.MealScheduleService;

@Service
public class MealScheduleServiceImpl implements MealScheduleService {

    private final MealScheduleRepository mealScheduleRepository;

    public MealScheduleServiceImpl(MealScheduleRepository mealScheduleRepository) {
        this.mealScheduleRepository = mealScheduleRepository;
    }

    @Override
    public MealSchedule saveMeal(MealSchedule meal) {
        return mealScheduleRepository.save(meal);
    }

    @Override
    public MealSchedule getByDate(LocalDate date) {
        return mealScheduleRepository.findByDate(date);
    }
    @Override
    public void deleteById(Long id) {
        mealScheduleRepository.deleteById(id);
    }
    @Override
    public List<MealSchedule> getAllMeals() {
        return mealScheduleRepository.findAllByOrderByDateAsc();
    }
}
