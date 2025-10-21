package com.example.TripTrack.services.ServiceInterfaces;

import com.example.TripTrack.dto.TripDTO;
import com.example.TripTrack.entities.Trip;

import java.util.List;
import java.util.UUID;

public interface TripServiceInterface {

    public List<TripDTO> findAll();
    public TripDTO findTripById(UUID id);
    public TripDTO save(Trip trip);
    public void deleteById(UUID id);
    public List<TripDTO> findByDaysGreaterThan(int numberOfDays);
    public List<TripDTO> findByDestination(String destination)
    public List<TripDTO> findByDaysLessThanEqual(int days)
    public TripDTO update(TripDTO tripDTO, UUID id)
}
