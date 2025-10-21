package com.example.TripTrack.services.ServiceInterfaces;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.entities.Transportation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransportationServiceInterface {
    public List<TransportationDTO> findAll();
    public Optional<TransportationDTO> findById(UUID id);
    public TransportationDTO save(Transportation transportation);
    public void deleteById(UUID id);
    public List<TransportationDTO> getAllTransportation(List<Transportation> transportation);
    public TransportationDTO update(TransportationDTO transportationDTO, UUID id);
}
