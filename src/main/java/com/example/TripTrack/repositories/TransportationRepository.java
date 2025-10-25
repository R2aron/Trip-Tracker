package com.example.TripTrack.repositories;

import com.example.TripTrack.entities.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransportationRepository extends JpaRepository<Transportation, UUID> {

    List<Transportation> findAllByParentId(UUID tripId);
}
