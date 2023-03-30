package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.CarEntity;
import ro.unibuc.hello.data.CarRepository;
import ro.unibuc.hello.dto.CarsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CarServiceTest {
    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService = new CarService(carRepository);

    CarEntity car;
    CarEntity updatedCar;
    CarEntity carWithoutId;
    CarsDTO carsDTO;
    CarsDTO updatedCarDTO;
    ArrayList<CarsDTO> carsDTOs;
    ArrayList<CarEntity>carEntities;
    String deleteResponse;

    List<String> reviewIds;
    List<String> watchItemIds;

    @BeforeEach
    void setUp(){
        car = new CarEntity("BMW", "Sedan", 2022, "Euro6",30000);
        updatedCar = new CarEntity("BMW","Sedan",2022,
                car.getCarEuro(),car.getCarPrice());
        carWithoutId = new CarEntity(car.getCarMaker(),car.getCarType(),car.getCarYear(),
                car.getCarEuro(),car.getCarPrice());
        car.setCarId("999999999999999999999999");
        updatedCar.setCarId(car.getCarId());
        deleteResponse = "Car with id " + car.getCarId() + " was deleted!";

        carsDTO = new CarsDTO(car);
        updatedCarDTO = new CarsDTO(updatedCar);

        carsDTOs = new ArrayList<>();
        carEntities = new ArrayList<>();

        carsDTOs.add(carsDTO);
        carEntities.add(car);
    }

    @Test
    void getCars() {
        doReturn(carEntities).when(carRepository).findAll();

        List<CarsDTO> result = carService.getCars();

        Assertions.assertEquals(result, carsDTOs);
    }

    @Test
    void getCar() {
        when(carRepository.findById(String.valueOf(new ObjectId(car.getCarId())))).thenReturn(Optional.ofNullable(car));

        CarsDTO result = carService.getCar(carsDTO.getCarId());

        Assertions.assertEquals(result, carsDTO);
    }

    @Test
    void insertCar() {
        when(carRepository.save(carWithoutId)).thenReturn(car);

        CarsDTO result = carService.insertCar(car.getCarMaker(),car.getCarType(),car.getCarYear(),
                car.getCarEuro(),car.getCarPrice());

        Assertions.assertEquals(result,carsDTO);
    }

    @Test
    void updateCar() {
        when(carRepository.findById(String.valueOf(new ObjectId(car.getCarId())))).thenReturn(Optional.ofNullable(car));
        when(carRepository.save(updatedCar)).thenReturn(updatedCar);

        CarsDTO result = carService.updateCar(car.getCarId(),"BMW","Sedan",2022,
                car.getCarEuro(),car.getCarPrice());

        Assertions.assertEquals(result, updatedCarDTO);
    }

    @Test
    void deleteCar() {
        String result = carService.deleteCar(car.getCarId());

        Assertions.assertEquals(result, deleteResponse);
    }


}
