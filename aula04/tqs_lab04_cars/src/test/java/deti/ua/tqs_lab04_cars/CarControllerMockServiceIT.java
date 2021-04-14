package deti.ua.tqs_lab04_cars;

import deti.ua.tqs_lab04_cars.JsonUtil;
import deti.ua.tqs_lab04_cars.CarController;
import deti.ua.tqs_lab04_cars.Car;
import deti.ua.tqs_lab04_cars.CarManagerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan({"deti.ua.tqs_lab04_cars.service", "deti.ua.tqs_lab04_cars.controller", "deti.ua.tqs_lab04_cars.entity", "deti.ua.tqs_lab04_cars.repository"})
@WebMvcTest(CarController.class)
class CarControllerMockServiceIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() {}

    @AfterEach
    public void tearDown() {}


    @Test
    public void whenPostCarCreateCar( ) throws Exception {
        Car ordinaryCar = new Car(1L, "Opel", "Corsa");

        //given(service.save(Mockito.any())).willReturn(alex);
        when(service.save(Mockito.any())).thenReturn(ordinaryCar);

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(ordinaryCar)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("Corsa")));

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car c1 = new Car(1L, "Opel", "Corsa");
        Car c2 = new Car(2L, "Seat", "Ibiza");
        Car c3 = new Car(3L, "Ford", "Fiesta");

        List<Car> allCars = Arrays.asList(c1, c2, c3);

        given(service.getAllCars()).willReturn(allCars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].model", is(c1.getModel()))).andExpect(jsonPath("$[1].model", is(c2.getModel())))
                .andExpect(jsonPath("$[2].model", is(c3.getModel())));
        verify(service, VerificationModeFactory.times(1)).getAllCars();

    }
}