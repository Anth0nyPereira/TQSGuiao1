package deti.ua.tqs_lab04_cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CarServiceMockCarRepositoryTest {

    // lenient is required because we load some expectations in the setup
    // that are not used in all the tests. As an alternative, the expectations
    // could move into each test method and be trimmed: no need for lenient
    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService service;

    @BeforeEach
    public void setUp() {
        Car c1 = new Car(1L, "Opel", "Corsa");
        Car c2 = new Car(2L, "Seat", "Ibiza");
        Car c3 = new Car(3L, "Ford", "Fiesta");

        List<Car> allCars = Arrays.asList(c1, c2, c3);

        Mockito.when(carRepository.findByCarId(c1.getCarId())).thenReturn(c1);
        Mockito.when(carRepository.findByCarId(c2.getCarId())).thenReturn(c2);
        Mockito.when(carRepository.findByCarId(999L)).thenReturn(null);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
    public void whenValidCarId_thenCarShouldBeFound() {
        Long id = 1L;
        Optional<Car> foundedCar = service.getCarDetails(id);
        Car car = foundedCar.get();
        assertThat(car.getCarId()).isEqualTo(id);
    }

    @Test
    public void whenInValidCarId_thenCarShouldNotBeFound() {
        Long inexistentId = 99L;
        Optional<Car> inexistentCar = service.getCarDetails(inexistentId);
        assertTrue(inexistentCar.isEmpty());
        assertThat(inexistentCar).isEmpty();
        verifyFindByCarIdIsCalledOnce(inexistentId);
    }

    @Test
    public void given3Cars_whengetAll_thenReturn3Cars() {
        Car c1 = new Car(1L, "Opel", "Corsa");
        Car c2 = new Car(2L, "Seat", "Ibiza");
        Car c3 = new Car(3L, "Ford", "Fiesta");

        List<Car> allCars = service.getAllCars();
        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains(c1.getMaker(), c2.getMaker(), c3.getMaker());
    }

    private void verifyFindByCarIdIsCalledOnce(Long longId) {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(longId);
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }
}