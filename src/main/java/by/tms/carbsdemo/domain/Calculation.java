package by.tms.carbsdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double grams;
    private double carbs;
    private String result;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Calculation(double grams, double carbs, String result, User user) {
        this.author = user;
        this.grams = grams;
        this.carbs = carbs;
        this.result = result;
    }


}
