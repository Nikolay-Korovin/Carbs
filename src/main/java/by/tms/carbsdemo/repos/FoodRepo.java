package by.tms.carbsdemo.repos;

import by.tms.carbsdemo.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepo extends JpaRepository<Food,Long> {
    List<Food> findByFoodName(String foodName);
}
