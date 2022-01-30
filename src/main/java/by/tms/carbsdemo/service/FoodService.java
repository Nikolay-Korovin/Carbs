package by.tms.carbsdemo.service;

import by.tms.carbsdemo.domain.Food;
import by.tms.carbsdemo.domain.User;
import by.tms.carbsdemo.repos.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepo foodRepo;

    public void save(Food food) {
        foodRepo.save(food);
    }

    public Iterable<Food> findByFoodName(String foodNameFilter) {
        return foodRepo.findByFoodName(foodNameFilter);
    }

    public List<Food> findFood(){
        return foodRepo.findAll();
    }

    public Iterable<Food> findUserFood(User user){
        List<Food> allFood = findFood();
        List<Food> sortedList = new ArrayList<>();
        for(Food food : allFood){
            if(food.getAuthor().getUsername().equals(user.getUsername())){
                sortedList.add(food);
            }
        }
        return sortedList;
    }

}
