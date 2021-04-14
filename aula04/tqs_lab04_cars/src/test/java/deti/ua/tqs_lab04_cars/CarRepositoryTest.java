package deti.ua.tqs_lab04_cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindCar_thenReturnIt() {
        Car c1 = new Car(1L, "Opel", "Corsa");
        entityManager.persistAndFlush(c1); //ensure data is persisted at this point

        Car found = carRepository.findByCarId(c1.getCarId());
        assertThat(found).isEqualTo(c1);
        assertThat(found).isNotNull();
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car inexistentCar = carRepository.findByCarId(-111L);
        assertThat(inexistentCar).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAll() {
        Car c1 = new Car(1L, "Opel", "Corsa");
        Car c2 = new Car(2L, "Seat", "Ibiza");
        Car c3 = new Car(3L, "Ford", "Fiesta");

        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(c3);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getCarId).containsOnly(c1.getCarId(), c2.getCarId(), c3.getCarId());
    }

}