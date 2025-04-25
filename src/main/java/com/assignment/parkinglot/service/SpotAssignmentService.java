package com.assignment.parkinglot.service;

import com.assignment.parkinglot.entity.Spot;
import com.assignment.parkinglot.repo.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotAssignmentService {
    @Autowired
    private SpotRepository spotRepository;

    public Spot assignSpot(String vehicleSize) {
        return spotRepository.findAvailableSpot(vehicleSize)
                .orElseThrow(() -> new RuntimeException("No available spots for size: " + vehicleSize));
    }
}
