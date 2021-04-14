package deti.ua.tqs_lab04_cars;

import deti.ua.tqs_lab04_cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {

    public Car findByCarId(Long longId);
    public List<Car> findAll();

}