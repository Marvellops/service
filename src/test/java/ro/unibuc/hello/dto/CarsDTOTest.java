package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.CarEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarsDTOTest {
    String carId; //id of the car in the database

    String carMaker;//Volvo, BMW, Renault, etc

    String carType; //sedan, SUV, StationWagon

    Integer carYear; //year the car is produced

    String carEuro; //if Euro6,5,etc

    Integer carPrice; // price of the car

    CarsDTO carsDTO;
    CarsDTO carsDTO2;

    int hash;
    String carsStringDTO;

    @BeforeEach
    void setUp(){
       carId= "111111111111111111111111111111111";
       carMaker= "Bentley";
       carType= "SUV";
       carYear= 2022;
       carEuro= "Euro6";
       carPrice= 4500000;
       carsDTO= new CarsDTO();
       carsDTO2 = new CarsDTO(new CarEntity(carMaker, carType, carYear, carEuro, carPrice));

       carsStringDTO="CarDTO{" +
                    "carId='" + carId + '\'' +
                    ", carMaker='" + carMaker + '\'' +
                    ", carType='" + carType + '\'' +
                    ", carYear='" + carYear + '\'' +
                    ", carEuro=" + carEuro +
                    ", carPrice=" + carPrice +
                    '}';
       carsDTO.setCarId(carId);
       carsDTO.setCarMaker(carMaker);
       carsDTO.setCarType(carType);
       carsDTO.setCarYear(carYear);
       carsDTO.setCarEuro(carEuro);
       carsDTO.setCarPrice(carPrice);
       carsDTO2.setCarId(carId);
    }




    @Test
    void getCarId() { Assertions.assertEquals(carId, carsDTO.getCarId());
    }

    @Test
    void getCarMaker() { Assertions.assertEquals(carMaker, carsDTO.getCarMaker());
    }

    @Test
    void getCarType() { Assertions.assertEquals(carType, carsDTO.getCarType());
    }

    @Test
    void getCarYear() { Assertions.assertEquals(carYear, carsDTO.getCarYear());
    }

    @Test
    void getCarEuro() { Assertions.assertEquals(carEuro, carsDTO.getCarEuro());
    }

    @Test
    void getCarPrice() { Assertions.assertEquals(carPrice, carsDTO.getCarPrice());
    }

    @Test
    void testEquals() { Assertions.assertEquals(carsDTO, carsDTO2);
    }


    @Test
    void testToString() { Assertions.assertEquals(carsStringDTO, carsDTO.toString());
    }
}