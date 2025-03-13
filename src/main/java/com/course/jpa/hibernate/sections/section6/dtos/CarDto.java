package com.course.jpa.hibernate.sections.section6.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private Long carIdNumber;
    private String carModel;
    private String carColor;
    private int carMileage;
    private int carNumberOfDoor;
    private String carTransmissionType;
    private String carFuelType;
    private String carLicensePlateNumber;
    private String carManufacturerName;
    private int carManufacturerYear;
    private EngineDto engine;  // Add this if you're passing Engine data
}
