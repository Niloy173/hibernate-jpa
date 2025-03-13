package com.course.jpa.hibernate.sections.section6.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Car")
@DynamicUpdate
public class Car {

    @Id
    @GeneratedValue(generator="car_sequence")
    @SequenceGenerator(name="car_sequence",sequenceName="car_sequence", allocationSize=1)
    @Column(name = "VEHICLE_IDENTIFICATION_NUMBER")
    private Long carIdNumber;

    @Column(name = "MODEL")
    private String carModel;

    @Column(name = "COLOR")
    private String carColor;

    @Column(name = "MILEAGE")
    private int carMileage;

    @Column(name = "NUMBER_OF_DOORS")
    private int carNumberOfDoor;

    @Column(name = "TRANSMISSION_TYPE")
    private String carTransmissionType;

    @Column(name = "FUEL_TYPE")
    private String carFuelType;

    @Column(name = "LICENSE_PLATE_NUMBER")
    private String carLicensePlateNumber;

    @Column(name = "MANUFACTURER_NAME")
    private String carManufacturerName;

    @Column(name = "MANUFACTURER_YEAR")
    private int carManufacturerYear;

    @OneToOne(mappedBy = "car", fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JsonManagedReference
    private Engine engine;


}
