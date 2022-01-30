package by.tms.carbsdemo.controller;

import by.tms.carbsdemo.domain.Calculation;
import by.tms.carbsdemo.domain.Food;
import by.tms.carbsdemo.domain.User;
import by.tms.carbsdemo.service.CalculateService;
import by.tms.carbsdemo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class MainPageController {

    @Autowired
    private CalculateService calculateService;
    @Autowired
    private FoodService foodService;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/mainpage")
    public String main(@AuthenticationPrincipal User user, Map<String, Optional<User>> model) {
        model.put("user", Optional.of(user));
        return "mainpage";
    }

    @PostMapping("/addfood")
    public String addFood(@AuthenticationPrincipal User user,
                          @RequestParam String foodName,
                          @RequestParam int calories,
                          @RequestParam double proteins,
                          @RequestParam double fats,
                          @RequestParam double carbs, Map<String, Object> model) {

        Food food = new Food(foodName, calories, proteins, fats, carbs, user);
        model.put("user", Optional.of(user));
        foodService.save(food);
        return "mainpage";
    }

    @PostMapping("/foodfilter")
    public String foodFilter(@AuthenticationPrincipal User user,
                             @RequestParam String foodNameFilter, Map<String, Object> model) {
        Iterable<Food> foods;
        if (foodNameFilter != null && !foodNameFilter.isEmpty()) {
            foods = foodService.findByFoodName(foodNameFilter);
        } else {
            foods = null;
        }
        model.put("user", Optional.of(user));
        model.put("foods", foods);
        return "mainpage";
    }

    @PostMapping("/calculate")
    public String calculate(@AuthenticationPrincipal User user,
                            @RequestParam(required = false, defaultValue = "0") double carbs,
                            @RequestParam(required = false, defaultValue = "0") double grams,
                            Map<String, Object> model) {
        String result = calculateService.calculate(grams, carbs);
        calculateService.save(new Calculation(carbs, grams, result, user));

        model.put("units", result);
        return "mainpage";
    }

    @GetMapping("/favoritefood")
    public String findFavoriteFood(@AuthenticationPrincipal User user,
                                   Map<String, Object> model){
        model.put("user", Optional.of(user));
        Iterable<Food> favoriteFoods = foodService.findUserFood(user);

        model.put("favoriteFood",favoriteFoods);
        return"mainpage";
    }
}