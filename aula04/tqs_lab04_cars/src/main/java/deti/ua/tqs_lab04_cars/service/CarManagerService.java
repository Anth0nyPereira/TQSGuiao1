package deti.ua.tqs_lab04_cars.service;

import deti.ua.tqs_lab04_cars.entity.Car;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarManagerService {

   //private CarRepository carRepository;

    public Car save(Car car) {
        return car;
    }

    public List<Car> getAllCars() {
        return null;
    }

    public Optional<Car> getCarDetails(Long longId) {
        return null;
    }
}
