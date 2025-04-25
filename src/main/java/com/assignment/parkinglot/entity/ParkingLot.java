package com.assignment.parkinglot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Name of the parking lot
    private int totalFloors;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<Floor> floors;

}