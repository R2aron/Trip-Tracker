package com.example.TripTrack.repositories;

import com.example.TripTrack.entities.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccommodationRepository extends JpaRepository<Accommodation, UUID> {
    List<Accommodation> findAllByParentId(UUID tripId);
}
