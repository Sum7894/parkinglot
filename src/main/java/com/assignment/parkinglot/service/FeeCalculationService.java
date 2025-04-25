package com.assignment.parkinglot.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class FeeCalculationService {
    public double calculateFee(LocalDateTime entryTime, LocalDateTime exitTime, double hourlyRate) {
        long hoursSpent = Duration.between(entryTime, exitTime).toHours();
        return Math.ceil(hoursSpent) * hourlyRate; // Ceil for full hours
    }
}