package com.hostelsphere.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostelsphere.app.entity.MealSchedule;
import com.hostelsphere.app.service.MealScheduleService;

@Controller
public class AdminMealController {

    private final MealScheduleService mealScheduleService;

    public AdminMealController(MealScheduleService mealScheduleService) {
        this.mealScheduleService = mealScheduleService;
    }

    // View meals + add form
    @GetMapping("/admin/meals")
    public String viewMeals(Model model) {

        List<MealSchedule> meals = mealScheduleService.getAllMeals();
        model.addAttribute("meals", meals);

        return "admin-meals";
    }

    // Save meal
    @PostMapping("/admin/meals/save")
    public String saveMeal(
            @RequestParam LocalDate date,
            @RequestParam String breakfast,
            @RequestParam String lunch,
            @RequestParam String dinner) {

        MealSchedule meal = new MealSchedule();
        meal.setDate(date);
        meal.setBreakfast(breakfast);
        meal.setLunch(lunch);
        meal.setDinner(dinner);

        mealScheduleService.saveMeal(meal);

        return "redirect:/admin/meals?success";
    }

    // Delete meal
    @GetMapping("/admin/meals/delete/{id}")
    public String deleteMeal(@PathVariable Long id) {
        mealScheduleService.deleteById(id);
        return "redirect:/admin/meals?deleted";
    }
}