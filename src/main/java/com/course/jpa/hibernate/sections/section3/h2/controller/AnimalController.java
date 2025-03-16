package com.course.jpa.hibernate.sections.section3.h2.controller;

import com.course.jpa.hibernate.sections.section3.h2.dao.AnimalDao;
import com.course.jpa.hibernate.sections.section3.h2.dto.AnimalDto;
import com.course.jpa.hibernate.sections.section3.h2.entity.Animal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/h2/animals")
public class AnimalController {

    private AnimalDao animalDao;

    public AnimalController(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    @GetMapping("/all")
    public List<Animal> getAllAnimals() {
        return this.animalDao.getAllAnimals();
    }

    @GetMapping("/animal/{animalId}")
    public AnimalDto getSingleAnimalInformation(@PathVariable("animalId") Long animalId) {
        return this.animalDao.getAnimalInfoByAnimalId(animalId);
    }

    @PostMapping("/create")
    public String createNewAnimal(@RequestBody Animal animal) {
        return this.animalDao.createNewAnimal(animal);
    }
}
