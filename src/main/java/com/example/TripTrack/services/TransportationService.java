package com.example.TripTrack.services;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.mappers.TransportationMapper;
import com.example.TripTrack.repositories.TransportationRepository;
import com.example.TripTrack.services.ServiceInterfaces.TransportationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransportationService implements TransportationServiceInterface {
    @Autowired
    TransportationRepository transportationRepository;

    @Override
    public List<TransportationDTO> findAll()
    {
        return TransportationMapper.toDtoList(transportationRepository.findAll());
    }

    @Override
    public TransportationDTO getById(UUID id)
    {
        return new TransportationDTO(transportationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportation not found")));
    }

    @Override
    public Transportation findById(UUID id)
    {
        return transportationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportation not found"));
    }

    @Override
    public TransportationDTO save(Transportation transportation)
    {
        return new TransportationDTO(transportationRepository.save(transportation));
    }

    @Override
    public void deleteById(UUID id)
    {
        transportationRepository.deleteById(id);
    }

    @Override
    public List<TransportationDTO> getAllTransportationDto(List<Transportation> transportation)
    {
        List<TransportationDTO> transportationDTOList = TransportationMapper.toDtoList(transportation);
        return transportationDTOList;
    }

    @Override
    public TransportationDTO update(TransportationDTO transportationDTO, UUID id)
    {
        Transportation entityToUpdate = findById(id);
        Transportation transportation = TransportationMapper.updateEntityFromDto(transportationDTO,entityToUpdate);
        return new TransportationDTO(transportationRepository.save(transportation));
    }

}
