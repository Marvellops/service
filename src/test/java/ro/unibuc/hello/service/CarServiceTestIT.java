package ro.unibuc.hello.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.CarEntity;
import ro.unibuc.hello.data.CarRepository;
import ro.unibuc.hello.dto.CarsDTO;

/*import org.mockito.Mock;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;*/

@SpringBootTest
@Tag("IT")
public class CarServiceTestIT {

    CarEntity updateCar;
    CarsDTO updateCarDTO;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarService carService;

    @BeforeEach
    void setUp(){
        updateCar = new CarEntity("BMW", "Sedan", 2022, "Euro6",30000);

        updateCarDTO = new CarsDTO(updateCar);

    }

    @Test
    void updateCar(){
        CarEntity testcar = carRepository.findAll().get(0); //getting the first car from the repository
        updateCar.setCarId(testcar.carId);

        CarsDTO result = carService.updateCar(updateCar.carId, "AltaMasina",
                "AIDriven", 2022, "Euro7", 100000);

        Assertions.assertEquals(result.getCarId(), updateCar.carId);
        Assertions.assertEquals(result.getCarMaker(), "AltaMasina");
        Assertions.assertEquals(result.getCarType(), "AIDriven");
        Assertions.assertEquals((int) result.getCarYear(), 2022);
        Assertions.assertEquals(result.getCarEuro(), "Euro7");
        Assertions.assertEquals((int) result.getCarPrice(), 100000);

    }


    /* Variant below was used previously, but is just a Mock Test, not Integration test
    * keeping it though for future reference */
/*    @Mock
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
    }*/

}
