package ro.unibuc.hello.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ro.unibuc.hello.exception.EntityNotFoundException;
import ro.unibuc.hello.service.CarService;
import ro.unibuc.hello.dto.CarsDTO;
import java.util.List;


@RestController
public class CarController {
    @Autowired
    private CarService carService;


    @GetMapping("/car/getAll")
    @ResponseBody
    @Timed(value = "hello.car.getAll.time", description = "Time taken to return all cars")
    @Counted(value = "hello.car.getAll.count", description = "Times all cars were returned")
    public List<CarsDTO> getCars(){
        return carService.getCars();
    }

    @GetMapping("/car/get")
    @ResponseBody
    public CarsDTO getCar(@RequestParam(name="carId") String id) {
        return carService.getCar(id);
    }

    @PostMapping("/car/insert")
    @ResponseBody
    public CarsDTO insertCar(@RequestParam(name="carMaker") String carMaker,
                            @RequestParam(name="carType") String carType,
                            @RequestParam(name="carYear") Integer carYear,
                            @RequestParam(name="carEuro") String carEuro,
                            @RequestParam(name="carPrice") Integer carPrice) {
        return carService.insertCar(carMaker, carType, carYear, carEuro, carPrice);
    }

    @PutMapping("/car/update")
    @ResponseBody
    public CarsDTO updateCar(@RequestParam(name="carId") String carId,
                             @RequestParam(name="carMaker") String carMaker,
                             @RequestParam(name="carType") String carType,
                             @RequestParam(name="carYear") Integer carYear,
                             @RequestParam(name="carEuro") String carEuro,
                             @RequestParam(name="carPrice") Integer carPrice) {
        return carService.updateCar(carId, carMaker, carType, carYear, carEuro, carPrice);
    }

    @DeleteMapping("/car/delete")
    @ResponseBody
    public String deleteCar(@RequestParam(name="carId") String id) {
        return carService.deleteCar(id);

    }

    @GetMapping("/car/filterByCarType")
    @ResponseBody
    public List<CarsDTO> filterCarsByCarType(@RequestParam(name = "carType") String carType){
        return carService.filterCarsByCarType(carType);
    }

    @GetMapping("/car/filterByCarPrice")
    @ResponseBody
    public List<CarsDTO> filterCarsByCarType(@RequestParam(name = "carPrice") int carPrice){
        return carService.filterCarsByCarPrice(carPrice);
    }

    @GetMapping("/car/orderAscendingByCarPrice")
    @ResponseBody
    public List<CarsDTO> orderCarsAscendingByCarPrice(){
        return carService.OrderCarsByPriceAscending();
    }




}
