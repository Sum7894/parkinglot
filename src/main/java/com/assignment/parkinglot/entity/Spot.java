package com.assignment.parkinglot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Spot {
    @Id
    private Long id;
    private String spotSize; // e.g., SMALL, MEDIUM, LARGE
    private boolean isAvailable;
    @ManyToOne
    private Floor floor;
    // getters and setters
}
