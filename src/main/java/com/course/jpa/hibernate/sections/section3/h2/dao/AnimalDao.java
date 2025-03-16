package com.course.jpa.hibernate.sections.section3.h2.dao;

import com.course.jpa.hibernate.sections.section3.h2.dto.AnimalDto;
import com.course.jpa.hibernate.sections.section3.h2.entity.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository

public class AnimalDao {

    private static final Logger log = LoggerFactory.getLogger(AnimalDao.class);
    private JdbcTemplate jdbcTemplate; // spring jdbc

    public AnimalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Animal> getAllAnimals() {

        List<Animal> allAnimal = new ArrayList<>();

        jdbcTemplate.query(
                "SELECT animalId, animalName, animalDescription FROM ANIMAL",
                (ResultSet rs) -> {

//                   do {
//                        Animal animal = new Animal();
//                        animal.setAnimalId(rs.getInt("animalId"));
//                        animal.setAnimalName(rs.getString("animalName"));
//                        animal.setAnimalDescription(rs.getString("animalDescription"));
//                        allAnimal.add(animal);
//                    } while (rs.next());
                        Animal animal = new Animal();
                        animal.setAnimalId(rs.getInt("animalId"));
                        animal.setAnimalName(rs.getString("animalName"));
                        animal.setAnimalDescription(rs.getString("animalDescription"));
                        allAnimal.add(animal);

                });

        return allAnimal;
    }


    public AnimalDto getAnimalInfoByAnimalId(Long animalId) {

        String sqlQuery = "SELECT ANIMALID, ANIMALNAME, ANIMALDESCRIPTION FROM ANIMAL WHERE ANIMALID = ?";
       try {
           return jdbcTemplate.queryForObject(
                   sqlQuery,
                   new Object[]{animalId},
                   (ResultSet rs, int rowNum) -> {
                       System.out.println(rs);
                       AnimalDto singleAnimal = new AnimalDto();
                       singleAnimal.setId(rs.getLong("ANIMALID"));
                       singleAnimal.setName(rs.getString("ANIMALNAME"));
                       singleAnimal.setDescription(rs.getString("ANIMALDESCRIPTION"));

                       return singleAnimal;
                   });
       } catch (Exception e) {
           log.info(e.getMessage());
           return new AnimalDto();
       }

    }


    public String createNewAnimal(Animal animal) {

        int rowUpdated = jdbcTemplate.update(
                "INSERT INTO ANIMAL (ANIMALNAME, ANIMALDESCRIPTION) VALUES" +
                        "(?, ?)" ,
                        animal.getAnimalName(), animal.getAnimalDescription()
        );

        if(rowUpdated > 0) {
            return "new entry created";
        }
        return "failed to create a new entry";
    }

}
