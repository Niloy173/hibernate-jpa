package com.course.jpa.hibernate.sections.section6.service;

import com.course.jpa.hibernate.sections.section6.dtos.CarDto;
import com.course.jpa.hibernate.sections.section6.entities.Car;
import com.course.jpa.hibernate.sections.section6.mapper.CarMapper;
import com.course.jpa.hibernate.sections.section6.repository.CarRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarMapper carMapper;
    private final CarRepo carRepo;

    public CarService(CarMapper carMapper, CarRepo carRepo) {
        this.carMapper = carMapper;
        this.carRepo = carRepo;
    }

    public String saveNewCar(CarDto carDto) {
        Car newCar = carMapper.dtoToEntity(carDto);
//        newCar.setCarIdNumber(carRepo.generateId());

        if(newCar.getEngine() != null) {
            newCar.getEngine().setCar(newCar);
        }

        try {
            carRepo.save(newCar);
        } catch (Exception e) {
            System.out.println("Error saving new car: " + e.getMessage());
        }

        return "created";
    }

    public CarDto getCarDetails(Long id) {
        Car car = carRepo.findCarByCarIdNumber(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        if (car == null) {
            return new CarDto();
        }

        CarDto response = carMapper.entityToDto(car);
        return response;


    }
}
