package com.course.jpa.hibernate.sections.section6.controller;

import com.course.jpa.hibernate.sections.section6.dtos.EngineDto;
import com.course.jpa.hibernate.sections.section6.entities.Engine;
import com.course.jpa.hibernate.sections.section6.service.EngineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/engines")
@RestController
public class EngineController {

    private final EngineService engineService;

    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @GetMapping("/all")
    public List<EngineDto> allEngines() {
        return engineService.getAllEngines();
    }
}
