package com.course.jpa.hibernate.sections.section6.service;

import com.course.jpa.hibernate.sections.section6.dtos.EngineDto;
import com.course.jpa.hibernate.sections.section6.entities.Engine;
import com.course.jpa.hibernate.sections.section6.mapper.CarMapper;
import com.course.jpa.hibernate.sections.section6.mapper.EngineMapper;
import com.course.jpa.hibernate.sections.section6.repository.CarRepo;
import com.course.jpa.hibernate.sections.section6.repository.EngineRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EngineService {

    private final EngineMapper engineMapper;
    private final EngineRepo engineRepo;

    public EngineService(EngineMapper engineMapper, EngineRepo engineRepo) {
        this.engineMapper = engineMapper;
        this.engineRepo = engineRepo;
    }

    public List<EngineDto> getAllEngines() {
        List<Engine> allEngines = this.engineRepo.findAll();
        List<EngineDto> result = new ArrayList<>();

        if(allEngines.isEmpty()) {
            return result;
        }
        allEngines.forEach(engine -> {
          //  System.out.println("Engine " + engine);
            EngineDto singleEngine = engineMapper.entityToDto(engine);
            result.add(singleEngine);
        });

        return result;
    }
}
