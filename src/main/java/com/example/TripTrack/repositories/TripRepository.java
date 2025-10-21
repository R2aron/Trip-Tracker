package com.example.TripTrack.repositories;

import com.example.TripTrack.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    Optional<List<Trip>> findByDaysGreaterThan(int numberOfDays);
    Optional<List<Trip>> findByDaysLessThanEqual(int numberOfDays);
    Optional<List<Trip>> findAllByDestination(String destination);
}
