package com.assignment.parkinglot.controller;

import com.assignment.parkinglot.entity.Spot;
import com.assignment.parkinglot.entity.Ticket;
import com.assignment.parkinglot.entity.Vehicle;
import com.assignment.parkinglot.repo.TicketRepository;
import com.assignment.parkinglot.service.FeeCalculationService;
import com.assignment.parkinglot.service.SpotAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private SpotAssignmentService spotAssignmentService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FeeCalculationService feeCalculationService;

    @PostMapping("/entry")
    public Ticket parkVehicle(@RequestBody Vehicle vehicle) {
        Spot assignedSpot = spotAssignmentService.assignSpot(vehicle.getVehicleSize());
        assignedSpot.setAvailable(false);

        // Save ticket details
        Ticket ticket = new Ticket();
        ticket.setLicensePlate(vehicle.getLicensePlate());
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setSpot(assignedSpot);

        return ticketRepository.save(ticket);
    }

    @PostMapping("/exit/{ticketId}")
    public double exitVehicle(@PathVariable Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setExitTime(LocalDateTime.now());

        // Calculate fee
        double fee = feeCalculationService.calculateFee(ticket.getEntryTime(), ticket.getExitTime(), 10.0);
        ticket.setParkingFee(fee);

        // Mark the spot as available
        Spot spot = ticket.getSpot();
        spot.setAvailable(true);

        ticketRepository.save(ticket);

        return fee;
    }
}
