package com.course.jpa.hibernate.sections.section6.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Engine")
@DynamicUpdate
public class Engine {

    @Id
    @GeneratedValue(generator="engine_sequence")
    @SequenceGenerator(name="engine_sequence",sequenceName="engine_sequence", allocationSize=1)
    @Column(name = "ENGINE_SERIAL_NUMBER")
    private Long engineSerialNumber;

    @Column(name = "ENGINE_TYPE")
    private String engineType;

    @Column(name = "ENGINE_HORSEPOWER")
    private String engineHorsePower;

    @Column(name = "ENGINE_MANUFACTURER")
    private String engineManufacturer;

    @Column(name = "ENGINE_COOLING_SYSTEM")
    private String engineCoolingSystem;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "CAR_VIN_NUMBER", referencedColumnName = "VEHICLE_IDENTIFICATION_NUMBER")
    private Car car;
}
