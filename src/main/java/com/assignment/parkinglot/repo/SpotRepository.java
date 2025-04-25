package com.assignment.parkinglot.repo;

import com.assignment.parkinglot.entity.Spot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {
    @Query("SELECT s FROM Spot s WHERE s.spotSize = :spotSize AND s.isAvailable = true ORDER BY s.floor.id ASC")
    Optional<Spot> findAvailableSpot(@Param("spotSize") String spotSize);
}
