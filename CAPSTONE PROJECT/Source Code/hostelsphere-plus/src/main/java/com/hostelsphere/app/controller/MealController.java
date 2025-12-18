package com.hostelsphere.app.controller;
import java.time.LocalDate; // Add this import at the top
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hostelsphere.app.entity.MealSchedule;
import com.hostelsphere.app.service.MealScheduleService;

@Controller
public class MealController {

    private final MealScheduleService mealScheduleService;

    public MealController(MealScheduleService mealScheduleService) {
        this.mealScheduleService = mealScheduleService;
    }

    @GetMapping("/meals")
    public String viewMeals(Model model) {
        List<MealSchedule> meals = mealScheduleService.getAllMeals();
        model.addAttribute("meals", meals);
        
        // Add today's meal if available
        MealSchedule todayMeal = mealScheduleService.getByDate(LocalDate.now());
        model.addAttribute("todayMeal", todayMeal);
        
        return "meals";
    }
}
