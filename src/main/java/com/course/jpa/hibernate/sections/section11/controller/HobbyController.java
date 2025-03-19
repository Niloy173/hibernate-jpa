package com.course.jpa.hibernate.sections.section11.controller;

import com.course.jpa.hibernate.entities.Hobby;
import com.course.jpa.hibernate.sections.section11.service.HobbyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hobbies")
public class HobbyController {

    private final HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @GetMapping("/all")
    public List<Hobby> getAllHobby() {
        return this.hobbyService.getAllHobbies();
    }

    @GetMapping("/hobby/{id}")
    public Hobby getAllHobby(@PathVariable("id") Long id) {
        return this.hobbyService.getHobbyById(id);
    }

    @PostMapping("/create")
    public String createHobby(@RequestBody Hobby hobby) {
        return this.hobbyService.createHobby(hobby);
    }
}
