package by.tms.carbsdemo.domain;

import by.tms.carbsdemo.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String foodName;
    private int calories;
    private double proteins;
    private double fats;
    private double carbs;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;


    public Food(String foodName, int calories, double proteins, double fats, double carbs, User author) {
        this.author = author;
        this.foodName = foodName;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "none";
    }


}
