package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.CarEntity;
import ro.unibuc.hello.data.CarRepository;
import ro.unibuc.hello.dto.CarsDTO;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Tag("IT")
public class CarServiceTestIT {

    @Mock
    CarRepository carRepository;

    @Test
    void updateCar(){

        CarEntity testcar = new CarEntity("Dacia",
                "Sedan", 2022, "Euro6", 10000);
        testcar.setCarId("88888888888888888888888888888888");
        when(carRepository.save(any())).thenReturn(testcar);
        CarsDTO carsDTO = new CarsDTO(testcar);

        when(carRepository.findById("88888888888888888888888888888888")).thenReturn(Optional.of(testcar));

        CarService carService= new CarService(carRepository) ;
        CarsDTO result = carService.updateCar("88888888888888888888888888888888", "Dacia",
                "Sedan", 2022, "Euro6", 11000);

        Assertions.assertEquals(result.getCarId(), "88888888888888888888888888888888");
        Assertions.assertEquals(result.getCarMaker(), "Dacia");
        Assertions.assertEquals(result.getCarType(), "Sedan");
        Assertions.assertEquals((int) result.getCarYear(), 2022);
        Assertions.assertEquals(result.getCarEuro(), "Euro6");
        Assertions.assertEquals((int) result.getCarPrice(), 11000);
    }
}
