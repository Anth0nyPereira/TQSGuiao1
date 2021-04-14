package deti.ua.tqs_lab04_cars;

import deti.ua.tqs_lab04_cars.Car;
import deti.ua.tqs_lab04_cars.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        carRepository.save(car);
        return car;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long longId) {
        return Optional.ofNullable(carRepository.findByCarId(longId));
    }
}
