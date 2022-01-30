package by.tms.carbsdemo.service;

import by.tms.carbsdemo.domain.Calculation;
import by.tms.carbsdemo.repos.CalculationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {
    @Autowired
    private CalculationRepo calculationRepo;

    public String calculate(double carbs, double grams) {
        double result = (carbs * grams/100) / 12;
        return String.format("%.2f", result);
    }

    public void save(Calculation calculation) {
        if(calculation.getCarbs()!=0 && calculation.getGrams()!=0) {
            calculationRepo.save(calculation);
        }
    }
}
