package com.course.jpa.hibernate.sections.section6.controller;

import com.course.jpa.hibernate.sections.section6.dtos.CarDto;
import com.course.jpa.hibernate.sections.section6.service.CarService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/get-car-information/{id}")
    public CarDto getCarInformation(@PathVariable("id") Long id) {
        return carService.getCarDetails(id);
    }

    @PostMapping("/create")
    public String createNewCar(@RequestBody CarDto carDto) {
        return carService.saveNewCar(carDto);
    }

    @DeleteMapping("/delete-car/{carId}")
    public String deleteCar(@PathVariable("carId") Long carId) {
        return carService.deleteCarByCarId(carId);
    }
}
