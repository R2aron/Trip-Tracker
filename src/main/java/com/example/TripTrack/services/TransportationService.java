package com.example.TripTrack.services;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.mappers.AccomodationMapper;
import com.example.TripTrack.mappers.TransportationMapper;
import com.example.TripTrack.repositories.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransportationService {
    @Autowired
    TransportationRepository transportationRepository;

    public List<Transportation> findAll()
    {
        return transportationRepository.findAll();
    }

    public Optional<Transportation> findById(UUID id)
    {
        return transportationRepository.findById(id);
    }

    public Transportation save(Transportation transportation)
    {
        return transportationRepository.save(transportation);
    }

    public void deleteById(UUID id)
    {
        transportationRepository.deleteById(id);
    }

    public List<TransportationDTO> getAllTransportation(List<Transportation> transportation)
    {
        List<TransportationDTO> transportationDTOList = TransportationMapper.toDto(transportation);
        return transportationDTOList;
    }

}
