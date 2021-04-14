package deti.ua.tqs_lab04_cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"deti.ua.tqs_lab04_cars.service", "deti.ua.tqs_lab04_cars.controller", "deti.ua.tqs_lab04_cars.entity", "deti.ua.tqs_lab04_cars.repository"})
public class TqsLab04CarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TqsLab04CarsApplication.class, args);
	}

}
