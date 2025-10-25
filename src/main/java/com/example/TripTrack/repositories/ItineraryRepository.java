package com.example.TripTrack.repositories;

import com.example.TripTrack.entities.ItineraryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ItineraryRepository extends JpaRepository<ItineraryItem, UUID> {
    Optional<ItineraryItem> findByDate(LocalDate date);
    Optional<ItineraryItem> findByLocation(String location);
    List<ItineraryItem> findAllByParentId(UUID tripId);

}
