package deti.ua.tqs_lab04_cars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

// switch AutoConfigureTestDatabase with TestPropertySource to use a real database
//@TestPropertySource( locations = "application-integrationtest.properties")

//@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CarControllerTemplateIT {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    public void whenValidInput_thenCreateCar() {
        Car c1 = new Car(1L, "Opel", "Corsa");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", c1, Car.class);
        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("Corsa");
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200()  {
        Car c1 = new Car(1L, "Opel", "Corsa");
        Car c2 = new Car(2L, "Seat", "Ibiza");

        repository.saveAndFlush(c1);
        repository.saveAndFlush(c2);

        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("Corsa", "Ibiza");
    }
}