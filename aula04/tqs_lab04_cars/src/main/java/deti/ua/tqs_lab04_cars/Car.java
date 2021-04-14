package deti.ua.tqs_lab04_cars;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {

    @Id
    private Long carId;

    @Size(min = 3, max = 15)
    private String maker;

    @Size(min = 3, max = 15)
    private String model;

    public Car() {
    }

    public Car(Long carId, String maker, String model) {
        this.carId = carId;
        this.maker = maker;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(maker, car.maker) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, maker, model);
    }
}
