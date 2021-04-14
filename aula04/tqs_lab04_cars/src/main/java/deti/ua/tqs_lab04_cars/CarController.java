package deti.ua.tqs_lab04_cars;

import deti.ua.tqs_lab04_cars.Car;
import deti.ua.tqs_lab04_cars.CarManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/cars" )
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car createdCar = carManagerService.save(car);
        return new ResponseEntity<>(createdCar, status);
    }

    @GetMapping(path="/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping(path="/car/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable Long longId) {
        HttpStatus status = HttpStatus.FOUND;
        Optional<Car> foundedCar = carManagerService.getCarDetails(longId);
        return new ResponseEntity<>(foundedCar, status);
    }

}