package com.course.jpa.hibernate.sections.section6.repository;

import com.course.jpa.hibernate.sections.section6.entities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepo extends JpaRepository<Engine,Long> {
}
