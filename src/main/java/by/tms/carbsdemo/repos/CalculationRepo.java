package by.tms.carbsdemo.repos;

import by.tms.carbsdemo.domain.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepo extends JpaRepository<Calculation,Long> {
}
