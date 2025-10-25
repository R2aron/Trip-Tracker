package com.example.TripTrack.services.ServiceInterfaces;

import com.example.TripTrack.dto.*;
import com.example.TripTrack.entities.Trip;

import java.util.List;
import java.util.UUID;

public interface TripServiceInterface {

    public List<TripDTO> findAll();
    public List<Trip> getAll();
    public TripDTO findById(UUID id);
    public Trip getById(UUID id);
    public TripDTO save(Trip trip);
    public void deleteById(UUID id);
    public List<TripDTO> findByDaysGreaterThan(int numberOfDays);
    public List<TripDTO> findByDestination(String destination);
    public List<TripDTO> findByDaysLessThanEqual(int days);
    public TripDTO update(TripDTO tripDTO, UUID id);
    public LightResponseDTO findLightResponseDtoById(UUID id);
    public List<LightResponseDTO> getLightResponseDtoList();
    public List<AccommodationDTO> getAllAccommodationDto(UUID id);
    public List<ItineraryDTO> getAllItineraryDto(UUID id);
    public List<TransportationDTO> getAllTransportationDto(UUID id);
    public <T extends BaseTripDto> TripDTO save(T dto);
}
