package com.assignment.parkinglot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licensePlate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    @OneToOne
    private Spot spot;

    private double parkingFee;
}
