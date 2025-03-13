package com.course.jpa.hibernate.sections.section6.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngineDto {

    private Long engineSerialNumber;
    private String engineType;
    private String engineHorsePower;
    private String engineManufacturer;
    private String engineCoolingSystem;
//    private Long carIdNumber;  // Add this field to hold the reference to the Car
}
