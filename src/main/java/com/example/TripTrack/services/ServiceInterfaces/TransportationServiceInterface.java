package com.example.TripTrack.services.ServiceInterfaces;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.entities.Transportation;

import java.util.List;
import java.util.UUID;

public interface TransportationServiceInterface {
    TransportationDTO save(UUID tripId, TransportationDTO dto);

    List<Transportation> findAllByParentId(UUID tripId);

    public TransportationDTO getById(UUID id);
    public Transportation findById(UUID id);
    public void deleteById(UUID id);
    public List<TransportationDTO> getAllTransportationDto(List<Transportation> transportation);
    public TransportationDTO update(TransportationDTO transportationDTO, UUID id);
}
